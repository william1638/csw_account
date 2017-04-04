package com.std.account.dto.req;

public class XN802410Req {

    // 申请人（必填）
    private String userId;

    // 申请兑换的虚拟币金额（必填）
    private String amount;

    // 虚拟币币种（必填）
    private String currency;

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

}
