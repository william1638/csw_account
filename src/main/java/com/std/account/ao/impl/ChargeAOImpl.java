package com.std.account.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.account.ao.IChargeAO;
import com.std.account.bo.IAccountBO;
import com.std.account.bo.IChargeBO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.Account;
import com.std.account.domain.Charge;
import com.std.account.enums.EBizType;
import com.std.account.enums.EBoolean;
import com.std.account.enums.ECurrency;
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
    public String doChargeOffline(Charge data, ECurrency currency) {
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
        String orderNo = chargeBO.saveChargeOffline(data);
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
    @Transactional
    public String doChargeOfflineWithoutApp(Charge data, ECurrency currency) {
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
        // 保存充值申请记录
        String orderNo = chargeBO.saveChargeOffline(data);
        // 一键审核通过
        chargeBO.refreshApproveOrder(orderNo, data.getApproveUser(), EBoolean
            .getBooleanResultMap().get(EBoolean.YES.getCode()), data
            .getApproveUser(), data.getRefNo(), data.getFee());
        if (ECurrency.CNY.equals(currency)) {
            accountBO.refreshAmount(data.getAccountNumber(), data.getAmount(),
                orderNo, EBizType.AJ_CZ);
        } else if (ECurrency.XNB.equals(currency)) {
            // 积分划拨
            accountBO.refreshAmount(data.getFromAccountNumber(),
                -data.getAmount(), orderNo, EBizType.AJ_GMKJF);
            accountBO.refreshAmount(data.getAccountNumber(), data.getAmount(),
                orderNo, EBizType.AJ_GMJJF);
        }
        return orderNo;
    }

    @Override
    @Transactional
    public void doApproveCharge(String chargeNo, String approveUser,
            String approveResult, String approveNote, String refNo, Long fee,
            ECurrency currency) {
        Charge cqOrder = chargeBO.getCharge(chargeNo);
        if (cqOrder == null) {
            throw new BizException("xn000001", "无对应订单");
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
            if (ECurrency.CNY.equals(currency)) {
                accountBO.refreshAmount(cqOrder.getAccountNumber(),
                    cqOrder.getAmount(), cqOrder.getCode(), EBizType.AJ_CZ);
            } else if (ECurrency.XNB.equals(currency)) {
                // 积分划拨
                accountBO.refreshAmount(cqOrder.getFromAccountNumber(),
                    -cqOrder.getAmount(), cqOrder.getCode(), EBizType.AJ_GMKJF);
                accountBO.refreshAmount(cqOrder.getAccountNumber(),
                    cqOrder.getAmount(), cqOrder.getCode(), EBizType.AJ_GMJJF);
            }
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
