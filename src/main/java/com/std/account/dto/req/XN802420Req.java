package com.std.account.dto.req;

public class XN802420Req {
    // 购买人（必填）
    private String userId;

    // 申请购买的虚拟币金额（必填）
    private String amount;

    // 虚拟币币种（必填）
    private String currency;

    // 支付类型（必填）
    private String payType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

}
