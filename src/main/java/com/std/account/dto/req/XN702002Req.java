package com.std.account.dto.req;

public class XN702002Req {
    // 登陆名（必填）
    private String loginName;

    // 登陆密码（必填）
    private String loginPwd;

    // 登陆类型（必填，1=主登陆，即记录登陆日志；非1=应用登陆，即不记录登陆日志）
    private String loginType;

    // 登陆ip（当loginType=1时，必填；当loginType！=1时，选填）
    private String loginIp;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

}
