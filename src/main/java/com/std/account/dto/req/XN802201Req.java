package com.std.account.dto.req;

public class XN802201Req {
    // 用户账号(必填)
    private String accountNumber;

    // 取现金额（精确到厘）(必填)
    private String amount;

    // 行别（alipay支付宝）(必填)
    private String bankCode;

    // 开户支行(非必填)
    private String subbranch;

    // 行别对应的卡号(必填)
    private String bankcardNo;

    // 交易密码(必填)
    private String tradePwd;

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

    public String getTradePwd() {
        return tradePwd;
    }

    public void setTradePwd(String tradePwd) {
        this.tradePwd = tradePwd;
    }

    public String getSubbranch() {
        return subbranch;
    }

    public void setSubbranch(String subbranch) {
        this.subbranch = subbranch;
    }

}
