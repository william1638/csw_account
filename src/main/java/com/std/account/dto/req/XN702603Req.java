package com.std.account.dto.req;

public class XN702603Req extends APageReq {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 94885794900572830L;

    // 账号(必传字段，因为只能查某个账号的流水)
    private String accountNumber;

    // 订单号（Long）
    private String cqNo;

    // 状态（ 1待审批;2审批不通过;3审批通过-待支付;4不用审批-待支付;5支付失败-待对账;6支付成功-待对账;7已对账）
    private String status;

    // 方向：充值=1,取现=0
    private String direction;

    // 渠道（1线上2线下）
    private String channel;

    // 行别（alipay支付宝）
    private String bankCode;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

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

}
