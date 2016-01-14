package com.std.account.domain;

import java.util.Date;

import com.std.account.dao.base.ABaseDO;

/**
 * @author: luoqi 
 * @since: 2015年3月7日 下午6:09:52 
 * @history:
 */
public class UserPicture extends ABaseDO {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -2566978935146363980L;

    private Date createDatetimeStart;

    private Date createDatetimeEnd;

    // ***************** db propertity ******************
    private Long id;

    private String userId;

    // 真实姓名
    private String realName;

    // 证件类型
    private String idKind;

    // 证件号码
    private String idNo;

    // 证件照正面
    private String idPic1;

    // 证件照背面
    private String idPic2;

    // 本人手持证件照片
    private String idUserPic;

    // 上传日期
    private Date createDatetime;

    // 验证日期
    private Date verifyDatetime;

    // 验证状态
    private String verifyStatus;

    // 验证人
    private String verifyUser;

    // 备注
    private String remark;

    public Date getCreateDatetimeStart() {
        return createDatetimeStart;
    }

    public void setCreateDatetimeStart(Date createDatetimeStart) {
        this.createDatetimeStart = createDatetimeStart;
    }

    public Date getCreateDatetimeEnd() {
        return createDatetimeEnd;
    }

    public void setCreateDatetimeEnd(Date createDatetimeEnd) {
        this.createDatetimeEnd = createDatetimeEnd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getVerifyDatetime() {
        return verifyDatetime;
    }

    public void setVerifyDatetime(Date verifyDatetime) {
        this.verifyDatetime = verifyDatetime;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getVerifyUser() {
        return verifyUser;
    }

    public void setVerifyUser(String verifyUser) {
        this.verifyUser = verifyUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
