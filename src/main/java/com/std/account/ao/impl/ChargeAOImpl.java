package com.std.account.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.account.ao.IChargeAO;
import com.std.account.bo.IAccountBO;
import com.std.account.bo.IChargeBO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.Charge;
import com.std.account.enums.EBizType;
import com.std.account.enums.EBoolean;
import com.std.account.enums.EFromType;
import com.std.account.enums.EOrderStatus;
import com.std.account.exception.BizException;

@Service
public class ChargeAOImpl implements IChargeAO {
    @Autowired
    IChargeBO chargeBO;

    @Autowired
    IAccountBO accountBO;

    @Override
    public Paginable<Charge> queryChargePage(int start, int limit,
            Charge condition) {
        return chargeBO.getPaginable(start, limit, condition);
    }

    @Override
    @Transactional
    public String doChargeOffline(String accountNumber, Long amount,
            String fromType, String fromCode, String pdf) {
        accountBO.getAccount(accountNumber);
        String orderNo = chargeBO.saveChargeOffline(accountNumber, amount,
            EFromType.getFromTypeMap().get(fromType), fromCode, pdf);
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
        // + "充值申请，现已进入审核阶段，请留意相关短信通知。", ESmsBizType.Charge
        // .getCode(), ESmsBizType.Charge.getValue());
        return orderNo;
    }

    @Override
    public String doChargeOfflineWithoutApp(String accountNumber, Long amount,
            String fromType, String fromCode, String pdf, String approveUser,
            String approveNote, String refNo) {
        accountBO.getAccount(accountNumber);
        String orderNo = chargeBO.saveChargeOffline(accountNumber, amount,
            EFromType.getFromTypeMap().get(fromType), fromCode, pdf);
        chargeBO.refreshApproveOrder(orderNo, approveUser, EBoolean
            .getBooleanResultMap().get(EBoolean.YES.getCode()), approveNote,
            refNo, 0L);

        accountBO.refreshAmount(accountNumber, amount, orderNo, EBizType.AJ_CZ);
        return orderNo;
    }

    @Override
    public void doApproveCharge(String chargeNo, String approveUser,
            String approveResult, String approveNote, String refNo, Long fee) {
        Charge cqOrder = chargeBO.getCharge(chargeNo);
        if (cqOrder == null) {
            throw new BizException("xn000001", "无对应充值订单");
        }
        if (!EOrderStatus.todoAPPROVE.getCode().equalsIgnoreCase(
            cqOrder.getStatus())) {
            throw new BizException("xn000001", "订单不处于待审批状态");
        }
        chargeBO.refreshApproveOrder(chargeNo, approveUser, EBoolean
            .getBooleanResultMap().get(approveResult), approveNote, refNo, fee);
        // 发送短信
        // Account account = accountBO.getAccount(cqOrder.getAccountNumber());
        // User user = userBO.getUser(account.getUserId());
        // String mobile = user.getMobile();
        if (EBoolean.YES.getCode().equalsIgnoreCase(approveResult)) {// 验证通过的话，就资金变动
            accountBO.refreshAmount(cqOrder.getAccountNumber(),
                cqOrder.getAmount(), cqOrder.getCode(), EBizType.AJ_CZ);
            // smsOutBO.sendSmsOut(
            // mobile,
            // "尊敬的"
            // + PhoneUtil.hideMobile(mobile)
            // + "用户,您于"
            // + DateUtil.dateToStr(cqOrder.getCreateDatetime(),
            // DateUtil.DATA_TIME_PATTERN_1) + "提交的"
            // + CalculationUtil.divi(cqOrder.getAmount())
            // + "充值申请，审核已经通过，资金已经到账，请登录个人中心查看。",
            // ESmsBizType.Charge_Yes.getCode(), ESmsBizType.Charge_Yes
            // .getValue());
        } else {
            // smsOutBO.sendSmsOut(
            // mobile,
            // "尊敬的 "
            // + PhoneUtil.hideMobile(mobile)
            // + "用户,您于"
            // + DateUtil.dateToStr(cqOrder.getCreateDatetime(),
            // DateUtil.DATA_TIME_PATTERN_1) + "提交的"
            // + CalculationUtil.divi(cqOrder.getAmount())
            // + "充值申请，审核尚未通过，原因：" + remark + "。",
            // ESmsBizType.Charge_No.getCode(), ESmsBizType.Charge_No
            // .getValue());
        }

    }
}
