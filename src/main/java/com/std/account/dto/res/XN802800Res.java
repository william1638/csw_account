package com.std.account.dto.res;

public class XN802800Res {
    // 账号
    private String accountNumber;

    // 对方系统
    private String oppositeSystem;

    // 指定账号（精确到厘）
    private Long amount;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOppositeSystem() {
        return oppositeSystem;
    }

    public void setOppositeSystem(String oppositeSystem) {
        this.oppositeSystem = oppositeSystem;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
