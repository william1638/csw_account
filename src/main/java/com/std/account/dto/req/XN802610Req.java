package com.std.account.dto.req;

public class XN802610Req {
    // 账号
    private String accountNumber;

    // 金额（精确到厘：正数是蓝补;负数是红冲）Long
    private String amount;

    // 申请人
    private String applyUser;

    // 申请说明
    private String applyNote;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplyNote() {
        return applyNote;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }

}
