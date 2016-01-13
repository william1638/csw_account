package com.std.account.dto.req;

public class XN702702Req extends APageReq {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -7706047277666843638L;

    // 订单号
    private String cqNo;

    // 状态（ 1待审批;2审批不通过;3审批通过-待支付;4不用审批-待支付;5支付失败-待对账;6支付成功-待对账;7已对账）
    private String status;

    // 方向：充值=1,取现=0
    private String direction;

    // 渠道（1线上2线下）
    private String channel;

    // 行别（alipay支付宝）
    private String bankCode;

    // 审批人（li为程序）
    private String approveUser;

    // 支付人（li为程序）
    private String payUser;

    // 对账人（li为程序）
    private String checkUser;

    // 理应对账时间(YYYY-MM-DD)
    private String workDate;

    // 账号
    private String accountNumber;

    // 手机号
    private String mobile;

    // 真实姓名
    private String realName;

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

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getApproveUser() {
        return approveUser;
    }

    public void setApproveUser(String approveUser) {
        this.approveUser = approveUser;
    }

    public String getPayUser() {
        return payUser;
    }

    public void setPayUser(String payUser) {
        this.payUser = payUser;
    }

    public String getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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
