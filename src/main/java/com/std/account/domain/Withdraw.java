/**
 * @Title CQOrder.java 
 * @Package com.ibis.account.domain 
 * @Description 
 * @author miyb  
 * @date 2015-2-12 下午8:39:10 
 * @version V1.0   
 */
package com.std.account.domain;

import java.util.Date;

import com.std.account.dao.base.ABaseDO;

/** 
 * 针对一个账号，充值取现
 * @author: miyb 
 * @since: 2015-2-12 下午8:39:10 
 * @history:
 */
public class Withdraw extends ABaseDO {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 7947903634077808099L;

    // ---------db properties start--------------------------
    // 订单号
    private String code;

    // 来方类型
    private String toType;

    // 开户支行
    private String toCode;

    // 归属
    private String toBelong;

    // 渠道
    private String channel;

    // 类型(货品商 1，下家 2)
    private String type;

    // 支付单号(支付成功时才有)
    private String refNo;

    // 金额（精确到厘）
    private Long amount;

    // 人民币价格
    private Long price;

    // 手续费（精确到厘）
    private Long fee;

    // 申请人
    private String applyUser;

    // 创建时间
    private Date createDatetime;

    // 状态
    private String status;

    // 审批人（li为程序）
    private String approveUser;

    // 审批说明
    private String approveNote;

    // 审批时间
    private Date approveDatetime;

    // 支付人（li为程序）
    private String payUser;

    // 支付说明
    private String payNote;

    // 支付时间
    private Date payDatetime;

    // 来方账号
    private String fromAccountNumber;

    // 账号
    private String accountNumber;

    // ---------db properties end--------------------------

    // 查询条件1：创建起始时间
    private Date createDatetimeStart;

    // 查询条件2：创建终止时间
    private Date createDatetimeEnd;

    // 真实姓名
    private String realName;

    // 来方用户编号
    private String fromUserId;

    // 去方用户编号
    private String toUserId;

    // 币种
    private String currency;

    // -----show properties end-----------
    public Date getCreateDatetimeStart() {
        return createDatetimeStart;
    }

    public void setCreateDatetimeStart(Date createDatetimeStart) {
        this.createDatetimeStart = createDatetimeStart;
    }

    public Date getCreateDatetimeEnd() {
        return createDatetimeEnd;
    }

    public void setCreateDatetimeEnd(Date createDatetimeEnd) {
        this.createDatetimeEnd = createDatetimeEnd;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToBelong() {
        return toBelong;
    }

    public void setToBelong(String toBelong) {
        this.toBelong = toBelong;
    }

    public String getToType() {
        return toType;
    }

    public void setToType(String toType) {
        this.toType = toType;
    }

    public String getToCode() {
        return toCode;
    }

    public void setToCode(String toCode) {
        this.toCode = toCode;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApproveUser() {
        return approveUser;
    }

    public void setApproveUser(String approveUser) {
        this.approveUser = approveUser;
    }

    public String getApproveNote() {
        return approveNote;
    }

    public void setApproveNote(String approveNote) {
        this.approveNote = approveNote;
    }

    public Date getApproveDatetime() {
        return approveDatetime;
    }

    public void setApproveDatetime(Date approveDatetime) {
        this.approveDatetime = approveDatetime;
    }

    public String getPayUser() {
        return payUser;
    }

    public void setPayUser(String payUser) {
        this.payUser = payUser;
    }

    public String getPayNote() {
        return payNote;
    }

    public void setPayNote(String payNote) {
        this.payNote = payNote;
    }

    public Date getPayDatetime() {
        return payDatetime;
    }

    public void setPayDatetime(Date payDatetime) {
        this.payDatetime = payDatetime;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
