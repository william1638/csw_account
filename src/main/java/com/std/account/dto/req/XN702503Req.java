package com.std.account.dto.req;

public class XN702503Req {
    // 商家编号(定位了哪个商家，商家本身的一系列个性化的信息也便有了，比如商家密钥，充值结果回调URL，商品名称，商品种类，商品描述，
    // 需要填写送货信息，商户扩展信息，是否需要应答机制等)--必填（目前只有个金所）
    private String p1MerId;

    // 用户账号--必填
    private String accountNumber;

    // 充值到账金额（精确到厘）--必填
    private String amount;

    // 充值手续费（精确到厘）--必填,但可以为0
    private String fee;

    // 行别(哪家银行)---选填
    private String bankCode;

    public String getP1MerId() {
        return p1MerId;
    }

    public void setP1MerId(String p1MerId) {
        this.p1MerId = p1MerId;
    }

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

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

}
