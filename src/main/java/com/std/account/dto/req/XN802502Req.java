package com.std.account.dto.req;

public class XN802502Req {

    // 账户编号(选填)
    private String accountNumber;

    // 系统编号(必填)
    private String systemCode;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

}
