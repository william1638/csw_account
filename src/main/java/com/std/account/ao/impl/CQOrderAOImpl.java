/**
 * @Title CQOrderAOImpl.java 
 * @Package com.ibis.account.ao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-17 下午9:33:57 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.account.ao.ICQOrderAO;
import com.std.account.bo.IAccountBO;
import com.std.account.bo.IBankCardBO;
import com.std.account.bo.ICQOrderBO;
import com.std.account.bo.ISmsOutBO;
import com.std.account.bo.IUserBO;
import com.std.account.bo.base.Paginable;
import com.std.account.common.DateUtil;
import com.std.account.common.PhoneUtil;
import com.std.account.domain.Account;
import com.std.account.domain.CQOrder;
import com.std.account.domain.User;
import com.std.account.enums.EBankCardStatus;
import com.std.account.enums.EBizType;
import com.std.account.enums.EBoolean;
import com.std.account.enums.EChannel;
import com.std.account.enums.EDirection;
import com.std.account.enums.EOrderStatus;
import com.std.account.enums.ESmsBizType;
import com.std.account.enums.EUser;
import com.std.account.exception.BizException;
import com.std.account.util.CalculationUtil;

/** 
 * @author: miyb 
 * @since: 2015-3-17 下午9:33:57 
 * @history:
 */
@Service
public class CQOrderAOImpl implements ICQOrderAO {
    @Autowired
    IAccountBO accountBO;

    @Autowired
    IUserBO userBO;

    @Autowired
    ICQOrderBO cqOrderBO;

    @Autowired
    ISmsOutBO smsOutBO;

    @Autowired
    IBankCardBO bankCardBO;

    @Override
    @Transactional
    public String doChargeOffline(String accountNumber, Long amount,
            String bankCode, String bankcardNo) {
        Account account = accountBO.getAccount(accountNumber);
        String orderNo = cqOrderBO.saveCQOrder(accountNumber,
            EDirection.PLUS.getCode(), amount, bankCode, null, bankcardNo,
            EChannel.OFFLINE.getCode());
        // 发送短信
        User user = userBO.getUser(account.getUserId());
        String mobile = user.getMobile();
        smsOutBO
            .sendSmsOut(
                mobile,
                "尊敬的"
                        + PhoneUtil.hideMobile(mobile)
                        + "用户,您于"
                        + DateUtil.dateToStr(new Date(),
                            DateUtil.DATA_TIME_PATTERN_1) + "提交的"
                        + CalculationUtil.divi(amount)
                        + "充值申请，现已进入审核阶段，请留意相关短信通知。", ESmsBizType.Charge
                    .getCode(), ESmsBizType.Charge.getValue());
        return orderNo;
    }

    @Override
    @Transactional
    public String doWithdrawOffline(String accountNumber, Long amount,
            String bankCode, String bankcardNo, String tradePwd,
            String subbranch) {
        Account account = accountBO.getAccount(accountNumber);
        // 验证交易密码
        userBO.checkTradePwd(account.getUserId(), tradePwd);
        String orderNo = cqOrderBO.saveCQOrder(accountNumber,
            EDirection.MINUS.getCode(), amount, bankCode, subbranch,
            bankcardNo, EChannel.OFFLINE.getCode());
        accountBO.freezeAmount(accountNumber, amount, EBizType.AJ_QX, orderNo);
        // 发送短信
        User user = userBO.getUser(account.getUserId());
        String mobile = user.getMobile();
        smsOutBO
            .sendSmsOut(
                mobile,
                "尊敬的"
                        + PhoneUtil.hideMobile(mobile)
                        + "用户,您于"
                        + DateUtil.dateToStr(new Date(),
                            DateUtil.DATA_TIME_PATTERN_1) + "提交的"
                        + CalculationUtil.divi(amount)
                        + "取现申请，现已进入审核阶段，请留意相关短信通知。", ESmsBizType.Withdraw
                    .getCode(), ESmsBizType.Withdraw.getValue());
        return orderNo;
    }

    @Override
    @Transactional
    public String doWithdrawOSS(String accountNumber, Long amount,
            String bankCode, String bankcardNo, String subbranch) {
        String orderNo = cqOrderBO.saveCQOrder(accountNumber,
            EDirection.MINUS.getCode(), amount, bankCode, subbranch,
            bankcardNo, EChannel.OFFLINE.getCode());
        accountBO.freezeAmount(accountNumber, amount, EBizType.AJ_QX, orderNo);
        return orderNo;
    }

    /** 
     * @see com.ibis.account.ao.ICQOrderAO#queryCQOrderPage(int, int, com.ibis.account.domain.CQOrder)
     */
    @Override
    public Paginable<CQOrder> queryCQOrderPage(int start, int limit,
            CQOrder condition) {
        return cqOrderBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<CQOrder> queryCQOrderList(CQOrder condition) {
        return cqOrderBO.queryCQOrderList(condition);
    }

    @Override
    public void doApproveCharge(String orderNo, String approveUser,
            String approveResult, String remark) {
        CQOrder cqOrder = cqOrderBO.getCQOrder(orderNo);
        if (cqOrder == null) {
            throw new BizException("xn000001", "无对应充值订单");
        }
        if (!EOrderStatus.todoAPPROVE.getCode().equalsIgnoreCase(
            cqOrder.getStatus())) {
            throw new BizException("xn000001", "订单不处于待审批状态");
        }
        cqOrderBO.refreshApproveOrder(orderNo, approveUser, approveResult,
            remark);
        // 发送短信
        Account account = accountBO.getAccount(cqOrder.getAccountNumber());
        User user = userBO.getUser(account.getUserId());
        String mobile = user.getMobile();
        if (EBoolean.YES.getCode().equalsIgnoreCase(approveResult)) {// 验证通过的话，就资金变动
            cqOrderBO.refreshPayOrder(orderNo, EUser.LI.getCode(),
                EBoolean.YES.getCode(), "审核即自动支付", "", 0L,
                DateUtil.getToday(DateUtil.DB_DATE_FORMAT_STRING));
            accountBO.refreshAmount(cqOrder.getAccountNumber(),
                cqOrder.getAmount(), EBizType.AJ_CZ.getCode(),
                cqOrder.getCqNo());
            smsOutBO.sendSmsOut(
                mobile,
                "尊敬的"
                        + PhoneUtil.hideMobile(mobile)
                        + "用户,您于"
                        + DateUtil.dateToStr(cqOrder.getCreateDatetime(),
                            DateUtil.DATA_TIME_PATTERN_1) + "提交的"
                        + CalculationUtil.divi(cqOrder.getAmount())
                        + "充值申请，审核已经通过，资金已经到账，请登录个人中心查看。",
                ESmsBizType.Charge_Yes.getCode(), ESmsBizType.Charge_Yes
                    .getValue());
        } else {
            smsOutBO.sendSmsOut(
                mobile,
                "尊敬的 "
                        + PhoneUtil.hideMobile(mobile)
                        + "用户,您于"
                        + DateUtil.dateToStr(cqOrder.getCreateDatetime(),
                            DateUtil.DATA_TIME_PATTERN_1) + "提交的"
                        + CalculationUtil.divi(cqOrder.getAmount())
                        + "充值申请，审核尚未通过，原因：" + remark + "。",
                ESmsBizType.Charge_No.getCode(), ESmsBizType.Charge_No
                    .getValue());
        }
    }

    @Override
    public void doApproveWithdraw(String withdrawNo, String approveUser,
            String approveResult, String approveNote) {

        CQOrder cqOrder = cqOrderBO.getCQOrder(withdrawNo);
        if (cqOrder == null) {
            throw new BizException("xn702514", "无对应充取订单");
        }
        if (!EOrderStatus.todoAPPROVE.getCode().equalsIgnoreCase(
            cqOrder.getStatus())) {
            throw new BizException("xn702514", "订单不处于待审批状态");
        }
        cqOrderBO.refreshApproveOrder(withdrawNo, approveUser, approveResult,
            approveNote);
        // 发送短信
        Account account = accountBO.getAccount(cqOrder.getAccountNumber());
        User user = userBO.getUser(account.getUserId());
        String mobile = user.getMobile();
        if (EBoolean.NO.getCode().equalsIgnoreCase(approveResult)) {// 且验证不通过的话，就资金变动
            accountBO.unfreezeAmount(cqOrder.getAccountNumber(),
                cqOrder.getAmount(), EBizType.AJ_JD, cqOrder.getCqNo(), true);
            smsOutBO.sendSmsOut(
                mobile,
                "尊敬的"
                        + PhoneUtil.hideMobile(mobile)
                        + "用户,您于"
                        + DateUtil.dateToStr(cqOrder.getCreateDatetime(),
                            DateUtil.DATA_TIME_PATTERN_1) + "提交的"
                        + CalculationUtil.divi(cqOrder.getAmount())
                        + "取现申请，审核尚未通过，原因：" + approveNote
                        + "，如有疑问，请联系客服：400-0008-139。", ESmsBizType.Withdraw_No
                    .getCode(), ESmsBizType.Withdraw_No.getValue());
        }
    }

    @Override
    public void doPayWithdraw(String withdrawNo, String payUser,
            String payResult, String payNote, String payNo, Long payFree,
            String workDate) {

        CQOrder cqOrder = cqOrderBO.getCQOrder(withdrawNo);
        if (cqOrder == null) {
            throw new BizException("xn702515", "无对应充取订单");
        }
        if (!EOrderStatus.APPROVE_YES.getCode().equalsIgnoreCase(
            cqOrder.getStatus())) {
            throw new BizException("xn702515", "订单不处于待支付状态");
        }
        if (EDirection.MINUS.getCode().equalsIgnoreCase(cqOrder.getDirection())) {
            cqOrderBO.refreshPayOrder(withdrawNo, payUser, payResult, payNote,
                payNo, payFree, workDate);
            // 刷新银行卡状态
            bankCardBO.refreshStatus(cqOrder.getBankCode(),
                cqOrder.getBankcardNo(), EBankCardStatus.CONFIRM_YES);
            // 发送短信
            Account account = accountBO.getAccount(cqOrder.getAccountNumber());
            User user = userBO.getUser(account.getUserId());
            String mobile = user.getMobile();
            if (EBoolean.YES.getCode().equalsIgnoreCase(payResult)) {
                accountBO.unfreezeAmount(cqOrder.getAccountNumber(),
                    cqOrder.getAmount(), EBizType.AJ_JD, cqOrder.getCqNo(),
                    false);
                smsOutBO.sendSmsOut(mobile,
                    "尊敬的" + PhoneUtil.hideMobile(mobile) + "用户，您提交的"
                            + CalculationUtil.divi(cqOrder.getAmount())
                            + "取现申请，支付成功。资金已转入你绑定的银行卡账户中，请注意核对。",
                    ESmsBizType.Withdraw_Yes.getCode(),
                    ESmsBizType.Withdraw_Yes.getValue());
            } else {
                accountBO.unfreezeAmount(cqOrder.getAccountNumber(),
                    cqOrder.getAmount(), EBizType.AJ_JD, cqOrder.getCqNo(),
                    true);
                smsOutBO.sendSmsOut(mobile,
                    "尊敬的" + PhoneUtil.hideMobile(mobile) + "用户,您提交的"
                            + CalculationUtil.divi(cqOrder.getAmount())
                            + "取现申请，支付失败，原因：" + payNote
                            + "，如有疑问，请联系客服：400-0008-139。",
                    ESmsBizType.Withdraw_No.getCode(),
                    ESmsBizType.Withdraw_No.getValue());
            }

        }

    }
}
