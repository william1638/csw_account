/**
 * @Title OrderAOImpl.java 
 * @Package com.ibis.account.ao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-5-6 下午3:13:50 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.account.ao.IOrderAO;
import com.std.account.bo.IAccountBO;
import com.std.account.bo.IBankCardBO;
import com.std.account.bo.ICQOrderBO;
import com.std.account.bo.IHLOrderBO;
import com.std.account.bo.ISmsOutBO;
import com.std.account.bo.IUserBO;
import com.std.account.bo.IXNBOrderBO;
import com.std.account.bo.IZZOrderBO;

/** 
 * @author: miyb 
 * @since: 2015-5-6 下午3:13:50 
 * @history:
 */
@Service
public class OrderAOImpl implements IOrderAO {
    @Autowired
    IAccountBO accountBO;

    @Autowired
    ICQOrderBO cqOrderBO;

    @Autowired
    IHLOrderBO hlOrderBO;

    @Autowired
    IXNBOrderBO xnbOrderBO;

    @Autowired
    IBankCardBO bankCardBO;

    @Autowired
    IZZOrderBO zzOrderBO;

    @Autowired
    ISmsOutBO smsOutBO;

    @Autowired
    IUserBO userBO;

    /** 
     * @see com.ibis.account.ao.IOrderAO#doApprove(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @Transactional
    public void doApprove(String orderType, String orderNo, String approveUser,
            String approveResult, String remark) {
        // StringValidater.validateBlank(orderType, orderNo, approveUser,
        // approveResult, remark);
        // EOrderType type = EOrderType.getOrderTypeMap().get(orderType);
        // if (type == null) {
        // throw new BizException("xn702514", "订单类别错误");
        // }
        // if (EOrderType.CQ.getCode().equalsIgnoreCase(orderType)) {
        // CQOrder cqOrder = cqOrderBO.getCQOrder(orderNo);
        // if (cqOrder == null) {
        // throw new BizException("xn702514", "无对应充取订单");
        // }
        // if (!EOrderStatus.UNAPPROVE.getCode().equalsIgnoreCase(
        // cqOrder.getStatus())) {
        // throw new BizException("xn702514", "订单不处于待审批状态");
        // }
        // cqOrderBO.refreshApproveOrder(orderNo, approveUser, approveResult,
        // remark);
        // // 发送短信
        // Account account = accountBO.getAccount(cqOrder.getAccountNumber());
        // User user = userBO.getUser(account.getUserId());
        // String mobile = user.getMobile();
        // // 充值
        // if (EDirection.PLUS.getCode().equalsIgnoreCase(
        // cqOrder.getDirection())) {
        // if (EBoolean.YES.getCode().equalsIgnoreCase(approveResult)) {//
        // 验证通过的话，就资金变动
        // cqOrderBO.refreshPayOrder(orderNo, EUser.LI.getCode(),
        // EBoolean.YES.getCode(), "审核即自动支付", "", 0L,
        // DateUtil.getToday(DateUtil.DB_DATE_FORMAT_STRING));
        // accountBO.refreshAmount(cqOrder.getAccountNumber(),
        // cqOrder.getAmount(), EBizType.AJ_CZ.getCode(),
        // cqOrder.getCqNo());
        // smsOutBO.sendSmsOut(
        // mobile,
        // "尊敬的"
        // + PhoneUtil.hideMobile(mobile)
        // + "用户,您于"
        // + DateUtil.dateToStr(
        // cqOrder.getCreateDatetime(),
        // DateUtil.DATA_TIME_PATTERN_1) + "提交的"
        // + CalculationUtil.divi(cqOrder.getAmount())
        // + "充值申请，审核已经通过，资金已经到账，请登录个人中心查看。",
        // ESmsBizType.Charge_Yes.getCode(),
        // ESmsBizType.Charge_Yes.getValue());
        // } else {
        // smsOutBO.sendSmsOut(
        // mobile,
        // "尊敬的 "
        // + PhoneUtil.hideMobile(mobile)
        // + "用户,您于"
        // + DateUtil.dateToStr(
        // cqOrder.getCreateDatetime(),
        // DateUtil.DATA_TIME_PATTERN_1) + "提交的"
        // + CalculationUtil.divi(cqOrder.getAmount())
        // + "充值申请，审核尚未通过，原因：" + remark + "。",
        // ESmsBizType.Charge_No.getCode(), ESmsBizType.Charge_No
        // .getValue());
        // }
        // }
        // // 取现
        // if (EDirection.MINUS.getCode().equalsIgnoreCase(
        // cqOrder.getDirection())) {
        // if (EBoolean.NO.getCode().equalsIgnoreCase(approveResult)) {//
        // 且验证不通过的话，就资金变动
        // accountBO.unfreezeAmount(cqOrder.getAccountNumber(),
        // cqOrder.getAmount(), EBizType.AJ_JD, cqOrder.getCqNo(),
        // true);
        // smsOutBO.sendSmsOut(
        // mobile,
        // "尊敬的"
        // + PhoneUtil.hideMobile(mobile)
        // + "用户,您于"
        // + DateUtil.dateToStr(
        // cqOrder.getCreateDatetime(),
        // DateUtil.DATA_TIME_PATTERN_1) + "提交的"
        // + CalculationUtil.divi(cqOrder.getAmount())
        // + "取现申请，审核尚未通过，原因：" + remark
        // + "，如有疑问，请联系客服：400-0008-139。",
        // ESmsBizType.Withdraw_No.getCode(),
        // ESmsBizType.Withdraw_No.getValue());
        // }
        // }
        // }
        // if (EOrderType.HL.getCode().equalsIgnoreCase(orderType)) {
        // HLOrder hlOrder = hlOrderBO.getHLOrder(orderNo);
        // if (hlOrder == null) {
        // throw new BizException("xn702514", "无对应充取订单");
        // }
        // if (!EOrderStatus.UNAPPROVE.getCode().equalsIgnoreCase(
        // hlOrder.getStatus())) {
        // throw new BizException("xn702514", "订单不处于待审批状态");
        // }
        // hlOrderBO.refreshApproveOrder(orderNo, approveUser, approveResult,
        // remark);
        // // 发送短信
        // Account account = accountBO.getAccount(hlOrder.getAccountNumber());
        // User user = userBO.getUser(account.getUserId());
        // String mobile = user.getMobile();
        // if (EDirection.PLUS.getCode().equalsIgnoreCase(// 蓝补
        // hlOrder.getDirection())) {
        // if (EBoolean.YES.getCode().equalsIgnoreCase(approveResult)) { // 资金变动
        // accountBO.refreshAmount(hlOrder.getAccountNumber(),
        // hlOrder.getAmount(), EBizType.AJ_LB.getCode(),
        // hlOrder.getHlNo());
        // smsOutBO
        // .sendSmsOut(
        // mobile,
        // "尊敬的"
        // + PhoneUtil.hideMobile(mobile)
        // + "用户，您的蓝补申请审核已通过，稍后工作人员将依据银行账单对您的账户金额进行调整，请注意核对。",
        // ESmsBizType.HongLan.getCode(),
        // ESmsBizType.HongLan.getValue());
        // } else {
        // smsOutBO
        // .sendSmsOut(
        // mobile,
        // "尊敬的"
        // + PhoneUtil.hideMobile(mobile)
        // + "用户，您的蓝补申请审核不通过，您的账户金额并未出现错误，请注意核对。如有疑问，请联系客服：400-0008-139。",
        // ESmsBizType.HongLan.getCode(),
        // ESmsBizType.HongLan.getValue());
        // }
        // } else {
        // if (EBoolean.YES.getCode().equalsIgnoreCase(approveResult)) { // 资金变动
        // accountBO.refreshAmount(hlOrder.getAccountNumber(),
        // -hlOrder.getAmount(), EBizType.AJ_HC.getCode(),
        // hlOrder.getHlNo());
        // smsOutBO
        // .sendSmsOut(
        // mobile,
        // "尊敬的"
        // + PhoneUtil.hideMobile(mobile)
        // + "用户，您的红冲申请审核已通过，稍后工作人员将依据银行账单对您的账户金额进行调整，请注意核对。",
        // ESmsBizType.HongLan.getCode(),
        // ESmsBizType.HongLan.getValue());
        // } else {
        // smsOutBO
        // .sendSmsOut(
        // mobile,
        // "尊敬的"
        // + PhoneUtil.hideMobile(mobile)
        // + "用户，您的红冲申请审核不通过，您的账户金额并未出现错误，请注意核对。如有疑问，请联系客服：400-0008-139。",
        // ESmsBizType.HongLan.getCode(),
        // ESmsBizType.HongLan.getValue());
        // }
        // }
        // }
    }

    /** 
     * @see com.ibis.account.ao.IOrderAO#doPay(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
     */
    @Override
    @Transactional
    public void doPay(String orderType, String orderNo, String payUser,
            String payResult, String remark, String payNo, Long payFee,
            String workDate) {
        // StringValidater.validateBlank(orderType, orderNo, payUser, payResult,
        // remark, workDate);
        // EOrderType type = EOrderType.getOrderTypeMap().get(orderType);
        // if (type == null) {
        // throw new BizException("xn702515", "订单类别错误");
        // }
        // if (EOrderType.CQ.getCode().equalsIgnoreCase(orderType)) {
        // CQOrder cqOrder = cqOrderBO.getCQOrder(orderNo);
        // if (cqOrder == null) {
        // throw new BizException("xn702515", "无对应充取订单");
        // }
        // if (!EOrderStatus.APPROVE_YES.getCode().equalsIgnoreCase(
        // cqOrder.getStatus())) {
        // throw new BizException("xn702515", "订单不处于待支付状态");
        // }
        // if (EDirection.MINUS.getCode().equalsIgnoreCase(
        // cqOrder.getDirection())) {
        // cqOrderBO.refreshPayOrder(orderNo, payUser, payResult, remark,
        // payNo, payFee, workDate);
        // // 刷新银行卡状态
        // bankCardBO.refreshStatus(cqOrder.getBankCode(),
        // cqOrder.getBankcardNo(), EBankCardStatus.CONFIRM_YES);
        // // 发送短信
        // Account account = accountBO.getAccount(cqOrder
        // .getAccountNumber());
        // User user = userBO.getUser(account.getUserId());
        // String mobile = user.getMobile();
        // if (EBoolean.YES.getCode().equalsIgnoreCase(payResult)) {
        // accountBO.unfreezeAmount(cqOrder.getAccountNumber(),
        // cqOrder.getAmount(), EBizType.AJ_JD, cqOrder.getCqNo(),
        // false);
        // smsOutBO.sendSmsOut(mobile,
        // "尊敬的" + PhoneUtil.hideMobile(mobile) + "用户，您提交的"
        // + CalculationUtil.divi(cqOrder.getAmount())
        // + "取现申请，支付成功。资金已转入你绑定的银行卡账户中，请注意核对。",
        // ESmsBizType.Withdraw_Yes.getCode(),
        // ESmsBizType.Withdraw_Yes.getValue());
        // } else {
        // accountBO.unfreezeAmount(cqOrder.getAccountNumber(),
        // cqOrder.getAmount(), EBizType.AJ_JD, cqOrder.getCqNo(),
        // true);
        // smsOutBO.sendSmsOut(mobile,
        // "尊敬的" + PhoneUtil.hideMobile(mobile) + "用户,您提交的"
        // + CalculationUtil.divi(cqOrder.getAmount())
        // + "取现申请，支付失败，原因：" + remark
        // + "，如有疑问，请联系客服：400-0008-139。",
        // ESmsBizType.Withdraw_No.getCode(),
        // ESmsBizType.Withdraw_No.getValue());
        // }
        // }
        // } else {
        // throw new BizException("xn702515", "订单类别错误");
        // }
    }
}
