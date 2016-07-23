/**
 * @Title WithdrawAOImpl.java 
 * @Package com.ibis.account.ao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-17 下午9:33:57 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.account.ao.IWithdrawAO;
import com.std.account.bo.IAccountBO;
import com.std.account.bo.IUserBO;
import com.std.account.bo.IWithdrawBO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.Account;
import com.std.account.domain.Withdraw;
import com.std.account.enums.EBizType;
import com.std.account.enums.EBoolean;
import com.std.account.enums.ECurrency;
import com.std.account.enums.EOrderStatus;
import com.std.account.enums.EToType;
import com.std.account.exception.BizException;

/** 
 * @author: miyb 
 * @since: 2015-3-17 下午9:33:57 
 * @history:
 */
@Service
public class WithdrawAOImpl implements IWithdrawAO {
    @Autowired
    IAccountBO accountBO;

    @Autowired
    IUserBO userBO;

    @Autowired
    IWithdrawBO withdrawBO;

    @Override
    public Paginable<Withdraw> queryWithdrawPage(int start, int limit,
            Withdraw condition) {
        return withdrawBO.getPaginable(start, limit, condition);
    }

    @Override
    @Transactional
    public String doWithdrawOSS(String accountNumber, Long amount,
            String toType, String toCode, String toBelong) {

        String orderNo = withdrawBO.saveWithdrawOffline(accountNumber, amount,
            EToType.getToTypeMap().get(toType), toCode, toBelong);
        accountBO
            .freezeAmount(accountNumber, amount, orderNo, EBizType.AJ_QXDJ);
        return orderNo;
    }

    /** 
     * @see com.std.account.ao.IWithdrawAO#doWithdrawOSS(com.std.account.domain.Withdraw)
     */
    @Override
    @Transactional
    public String doWithdrawOSS(Withdraw data, ECurrency currency) {
        if (ECurrency.CNY.equals(currency)) {
            // 校验账户是否存在
            accountBO.getAccount(data.getFromAccountNumber());
            accountBO.getAccount(data.getAccountNumber());
        } else if (ECurrency.XNB.equals(currency)) {
            // 校验账户是否存在,并赋值
            Account fromAccount = accountBO.getAccountByUser(
                data.getFromUserId(), currency.getCode());
            data.setFromAccountNumber(fromAccount.getAccountNumber());
            // 校验账户是否存在,并赋值
            Account toAccount = accountBO.getAccountByUser(data.getToUserId(),
                currency.getCode());
            data.setAccountNumber(toAccount.getAccountNumber());
        }
        String orderNo = withdrawBO.saveWithdrawOffline(data);
        if (ECurrency.CNY.equals(currency)) {
            accountBO.freezeAmount(data.getAccountNumber(), data.getAmount(),
                orderNo, EBizType.AJ_QXDJ);
        } else if (ECurrency.XNB.equals(currency)) {
            accountBO.freezeAmount(data.getFromAccountNumber(),
                data.getAmount(), orderNo, EBizType.AJ_DXDJ);
        }
        return orderNo;
    }

    @Override
    @Transactional
    public String doWithdrawOffline(String accountNumber, Long amount,
            String toType, String toCode, String toBelong, String tradePwd) {
        Account account = accountBO.getAccount(accountNumber);
        // 验证交易密码
        userBO.checkTradePwd(account.getUserId(), tradePwd);
        String orderNo = withdrawBO.saveWithdrawOffline(accountNumber, amount,
            EToType.getToTypeMap().get(toType), toCode, toBelong);
        accountBO
            .freezeAmount(accountNumber, amount, orderNo, EBizType.AJ_QXDJ);
        // 发送短信
        // User user = userBO.getUser(account.getUserId());
        // String mobile = user.getMobile();
        // smsOutBO
        // .sendSmsOut(
        // mobile,
        // "尊敬的"
        // + PhoneUtil.hideMobile(mobile)
        // + "用户,您于"
        // + DateUtil.dateToStr(new Date(),
        // DateUtil.DATA_TIME_PATTERN_1) + "提交的"
        // + CalculationUtil.divi(amount)
        // + "取现申请，现已进入审核阶段，请留意相关短信通知。", ESmsBizType.Withdraw
        // .getCode(), ESmsBizType.Withdraw.getValue());
        return orderNo;
    }

    @Override
    @Transactional
    public void doApproveWithdraw(String withdrawNo, String approveUser,
            String approveResult, String approveNote, ECurrency currency) {
        Withdraw withdraw = withdrawBO.getWithdraw(withdrawNo);
        if (withdraw == null) {
            throw new BizException("xn702514", "无对应充取订单");
        }
        if (!EOrderStatus.todoAPPROVE.getCode().equalsIgnoreCase(
            withdraw.getStatus())) {
            throw new BizException("xn702514", "订单不处于待审批状态");
        }
        withdrawBO.refreshApproveOrder(withdrawNo, approveUser, EBoolean
            .getBooleanResultMap().get(approveResult), approveNote);
        // 发送短信
        // Account account = accountBO.getAccount(withdraw.getAccountNumber());
        // User user = userBO.getUser(account.getUserId());
        // String mobile = user.getMobile();
        if (EBoolean.NO.getCode().equalsIgnoreCase(approveResult)) {// 且验证不通过的话，就资金变动
            if (ECurrency.CNY.equals(currency)) {
                accountBO.unfreezeAmountToAmount(withdraw.getAccountNumber(),
                    withdraw.getAmount(), withdraw.getCode(), EBizType.AJ_QXJD);
            } else if (ECurrency.XNB.equals(currency)) {
                accountBO.unfreezeAmountToAmount(
                    withdraw.getFromAccountNumber(), withdraw.getAmount(),
                    withdraw.getCode(), EBizType.AJ_DXJD);
            }

            // smsOutBO.sendSmsOut(
            // mobile,
            // "尊敬的"
            // + PhoneUtil.hideMobile(mobile)
            // + "用户,您于"
            // + DateUtil.dateToStr(withdraw.getCreateDatetime(),
            // DateUtil.DATA_TIME_PATTERN_1) + "提交的"
            // + CalculationUtil.divi(withdraw.getAmount())
            // + "取现申请，审核尚未通过，原因：" + approveNote
            // + "，如有疑问，请联系客服：400-0008-139。", ESmsBizType.Withdraw_No
            // .getCode(), ESmsBizType.Withdraw_No.getValue());
        }
    }

    @Override
    @Transactional
    public void doPayWithdraw(String withdrawNo, String payUser,
            String payResult, String payNote, String refNo, Long fee,
            ECurrency currency) {
        Withdraw withdraw = withdrawBO.getWithdraw(withdrawNo);
        if (withdraw == null) {
            throw new BizException("xn702515", "无对应充取订单");
        }
        if (!EOrderStatus.APPROVE_YES.getCode().equalsIgnoreCase(
            withdraw.getStatus())) {
            throw new BizException("xn702515", "订单不处于待支付状态");
        }

        withdrawBO.refreshPayOrder(withdrawNo, payUser, EBoolean
            .getBooleanResultMap().get(payResult), payNote, refNo, fee);
        // 刷新银行卡状态
        // userBO.refreshStatus(withdraw.getBankCode(),
        // withdraw.getBankcardNo(),
        // EBankCardStatus.CONFIRM_YES);
        // 发送短信
        // Account account = accountBO.getAccount(withdraw.getAccountNumber());
        // User user = userBO.getUser(account.getUserId());
        // String mobile = user.getMobile();
        if (EBoolean.YES.getCode().equalsIgnoreCase(payResult)) {
            if (ECurrency.CNY.equals(currency)) {
                accountBO.unfreezeAmount(withdraw.getAccountNumber(),
                    withdraw.getAmount(), withdraw.getCode(), EBizType.AJ_QXCG);
            } else if (ECurrency.XNB.equals(currency)) {
                accountBO
                    .unfreezeAmount(withdraw.getFromAccountNumber(),
                        withdraw.getAmount(), withdraw.getCode(),
                        EBizType.AJ_DXJJF);
                accountBO.refreshAmount(withdraw.getAccountNumber(),
                    -withdraw.getAmount(), withdraw.getCode(),
                    EBizType.AJ_DXKJF);
            }
            // smsOutBO.sendSmsOut(mobile,
            // "尊敬的" + PhoneUtil.hideMobile(mobile) + "用户，您提交的"
            // + CalculationUtil.divi(withdraw.getAmount())
            // + "取现申请，支付成功。资金已转入你绑定的银行卡账户中，请注意核对。",
            // ESmsBizType.Withdraw_Yes.getCode(),
            // ESmsBizType.Withdraw_Yes.getValue());
        } else {
            if (ECurrency.CNY.equals(currency)) {
                accountBO.unfreezeAmountToAmount(withdraw.getAccountNumber(),
                    withdraw.getAmount(), withdraw.getCode(), EBizType.AJ_QXJD);
            } else if (ECurrency.XNB.equals(currency)) {
                accountBO.unfreezeAmountToAmount(
                    withdraw.getFromAccountNumber(), withdraw.getAmount(),
                    withdraw.getCode(), EBizType.AJ_DXJD);
            }
            // smsOutBO.sendSmsOut(mobile,
            // "尊敬的" + PhoneUtil.hideMobile(mobile) + "用户,您提交的"
            // + CalculationUtil.divi(withdraw.getAmount())
            // + "取现申请，支付失败，原因：" + payNote
            // + "，如有疑问，请联系客服：400-0008-139。",
            // ESmsBizType.Withdraw_No.getCode(),
            // ESmsBizType.Withdraw_No.getValue());
        }

    }
}
