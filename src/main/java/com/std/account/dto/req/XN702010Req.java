package com.std.account.dto.req;

public class XN702010Req {
    // userId（必填）
    private String userId;

    // 真实姓名（必填）
    private String realName;

    // 证件类型（必填）
    private String idKind;

    // 证件号码（必填）
    private String idNo;

    // 照片1（必填）
    private String idPic1;

    // 照片2（必填）
    private String idPic2;

    // 照片3（必填）
    private String idUserPic;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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

    public String getIdPic1() {
        return idPic1;
    }

    public void setIdPic1(String idPic1) {
        this.idPic1 = idPic1;
    }

    public String getIdPic2() {
        return idPic2;
    }

    public void setIdPic2(String idPic2) {
        this.idPic2 = idPic2;
    }

    public String getIdUserPic() {
        return idUserPic;
    }

    public void setIdUserPic(String idUserPic) {
        this.idUserPic = idUserPic;
    }

}
