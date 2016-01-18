package com.std.account.dto.req;

public class XN802612Req {
    // 流水号(必填)
    private String ajNo;

    // 对账人(必填)
    private String checkUser;

    // 金额（精确到厘：正数是蓝补;负数是红冲；0表示账已平）Long
    private String amount;

    public String getAjNo() {
        return ajNo;
    }

    public void setAjNo(String ajNo) {
        this.ajNo = ajNo;
    }

    public String getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
