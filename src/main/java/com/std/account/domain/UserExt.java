package com.std.account.domain;

import com.std.account.dao.base.ABaseDO;

/**
 * @author: luoqi 
 * @since: 2015年3月7日 下午5:57:41 
 * @history:
 */
public class UserExt extends ABaseDO {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -7058891538803599027L;

    // userId
    private String userId;

    // 头像照片
    private String photo;

    // 来自哪里
    private String comefrom;

    // 出生日期
    private String birthday;

    // qq号码
    private String qq;

    // 邮箱
    private String email;

    // 职业
    private String occupation;

    // 学历
    private String education;

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

    public String getComefrom() {
        return comefrom;
    }

    public void setComefrom(String comefrom) {
        this.comefrom = comefrom;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Override
    public String toString() {
        return "UserExtDO [userId=" + userId + ", photo=" + photo
                + ", comefrom=" + comefrom + ", birthday=" + birthday + ", qq="
                + qq + ", email=" + email + ", occupation=" + occupation
                + ", education=" + education + "]";
    }

}
