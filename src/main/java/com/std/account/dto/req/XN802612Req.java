package com.std.account.dto.req;

public class XN802612Req {
    // 流水号(必填)
    private String ajNo;

    // 对账人(必填)
    private String checkUser;

    // 方向：1=蓝补；0=红冲(必填)
    private String direction;

    // 金额（精确到厘）(必填)
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
