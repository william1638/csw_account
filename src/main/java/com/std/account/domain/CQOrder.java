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
 * @author: miyb 
 * @since: 2015-2-12 下午8:39:10 
 * @history:
 */
public class CQOrder extends ABaseDO {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 7947903634077808099L;

    // 查询条件1：创建起始时间
    private Date createDatetimeStart;

    // 查询条件2：创建终止时间
    private Date createDatetimeEnd;

    // ---------db properties start--------------------------
    // 订单号
    private String cqNo;

    // 状态
    private String status;

    // 方向：充值=1,取现=0
    private String direction;

    // 渠道（1线上2线下）
    private String channel;

    // 金额（精确到厘：正数是充值;负数是取现）
    private Long amount;

    // 行别（alipay支付宝）
    private String bankCode;

    // 开户支行
    private String subbranch;

    // 行别对应的卡号
    private String bankcardNo;

    // 创建时间
    private Date createDatetime;

    // 审批人（li为程序）
    private String approveUser;

    // 审批时间
    private Date approveDatetime;

    // 支付人（li为程序）
    private String payUser;

    // 支付时间
    private Date payDatetime;

    // 支付单号(支付成功时才有)
    private String payNo;

    // 支付手续费(支付成功时才有)
    private Long payFee;

    // 理应对账时间
    private String workDate;

    // 对账人（li为程序）
    private String checkUser;

    // 实际对账时间
    private Date checkDatetime;

    // 备注
    private String remark;

    // 账号
    private String accountNumber;

    // ---------db properties end--------------------------

    // -----show properties start-----------
    // 手机号
    private String mobile;

    // 真实姓名
    private String realName;

    // -----show properties end-----------

    public String getCqNo() {
        return cqNo;
    }

    public void setCqNo(String cqNo) {
        this.cqNo = cqNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankcardNo() {
        return bankcardNo;
    }

    public void setBankcardNo(String bankcardNo) {
        this.bankcardNo = bankcardNo;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getApproveUser() {
        return approveUser;
    }

    public void setApproveUser(String approveUser) {
        this.approveUser = approveUser;
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

    public Date getPayDatetime() {
        return payDatetime;
    }

    public void setPayDatetime(Date payDatetime) {
        this.payDatetime = payDatetime;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public Long getPayFee() {
        return payFee;
    }

    public void setPayFee(Long payFee) {
        this.payFee = payFee;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }

    public Date getCheckDatetime() {
        return checkDatetime;
    }

    public void setCheckDatetime(Date checkDatetime) {
        this.checkDatetime = checkDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

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

    public String getSubbranch() {
        return subbranch;
    }

    public void setSubbranch(String subbranch) {
        this.subbranch = subbranch;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
