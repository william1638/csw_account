package com.std.account.domain;

import java.util.Date;

import com.std.account.dao.base.ABaseDO;

/**
 * 短信验证码发送
 * @author: xieyj 
 * @since: 2015-3-8 下午8:12:20 
 * @history:
 */
public class SmsCaptcha extends ABaseDO {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 2932814833416456824L;

    // ********* 查询字段 ***********
    Date createDatetimeStart;

    Date createDatetimeEnd;

    // ********* 查询字段 ***********

    // id
    private Long id;

    // 手机
    private String mobile;

    // 业务类型
    private String bizType;

    // 短信验证码
    private String smsCaptcha;

    // 状态
    private String status;

    // 创建时间
    private Date createDatetime;

    // 发送时间
    private Date sentDatetime;

    // 失效时间
    private Date invalidDatetime;

    // 检查时间
    private Date checkDatetime;

    // 检查次数
    private int checkTimes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getSmsCaptcha() {
        return smsCaptcha;
    }

    public void setSmsCaptcha(String smsCaptcha) {
        this.smsCaptcha = smsCaptcha;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getSentDatetime() {
        return sentDatetime;
    }

    public void setSentDatetime(Date sentDatetime) {
        this.sentDatetime = sentDatetime;
    }

    public Date getInvalidDatetime() {
        return invalidDatetime;
    }

    public void setInvalidDatetime(Date invalidDatetime) {
        this.invalidDatetime = invalidDatetime;
    }

    public Date getCheckDatetime() {
        return checkDatetime;
    }

    public void setCheckDatetime(Date checkDatetime) {
        this.checkDatetime = checkDatetime;
    }

    public int getCheckTimes() {
        return checkTimes;
    }

    public void setCheckTimes(int checkTimes) {
        this.checkTimes = checkTimes;
    }

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

    @Override
    public String toString() {
        return "SmsCaptchaDO [id=" + id + ", mobile=" + mobile + ", bizType="
                + bizType + ", smsCaptcha=" + smsCaptcha + ", status=" + status
                + ", createDatetime=" + createDatetime + ", sentDatetime="
                + sentDatetime + ", invalidDatetime=" + invalidDatetime
                + ", checkDatetime=" + checkDatetime + ", checkTimes="
                + checkTimes + "]";
    }

}
