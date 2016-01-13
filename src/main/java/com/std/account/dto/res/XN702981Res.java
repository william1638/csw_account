package com.std.account.dto.res;

public class XN702981Res {
    // 最好不是手机号，就是一般的登录名。目前第一版版本先用手机号，因为用一般登录名，注册流程以及手机验证就要变
    private String loginName;

    private String userId;

    // 头像
    private String photo;

    // 状态
    private String status;

    // 是否手机绑定
    private String mobileFlag;

    // 是否实名认证
    private String identityFlag;

    // 是否设置交易密码
    private String tradepwdFlag;

    // 是否绑定银行卡
    private String bankcardFlag;

    // 证件类型
    private String idKind;

    // 证件号码
    private String idNo;

    // 真实姓名
    private String realName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMobileFlag() {
        return mobileFlag;
    }

    public void setMobileFlag(String mobileFlag) {
        this.mobileFlag = mobileFlag;
    }

    public String getIdentityFlag() {
        return identityFlag;
    }

    public void setIdentityFlag(String identityFlag) {
        this.identityFlag = identityFlag;
    }

    public String getTradepwdFlag() {
        return tradepwdFlag;
    }

    public void setTradepwdFlag(String tradepwdFlag) {
        this.tradepwdFlag = tradepwdFlag;
    }

    public String getBankcardFlag() {
        return bankcardFlag;
    }

    public void setBankcardFlag(String bankcardFlag) {
        this.bankcardFlag = bankcardFlag;
    }

    public String getIdKind() {
        return idKind;
    }

    public void setIdKind(String idKind) {
        this.idKind = idKind;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

}
