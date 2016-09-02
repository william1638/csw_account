package com.std.account.dto.req;

public class XN802012Req {
    // userId（必填）
    private String userId;

    // 币种（必填）
    private String currency;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
