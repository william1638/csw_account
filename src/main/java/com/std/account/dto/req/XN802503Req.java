package com.std.account.dto.req;

public class XN802503Req {

    // 账户编号(必填)
    private String userId;

    // 系统编号(必填)
    private String systemCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

}
