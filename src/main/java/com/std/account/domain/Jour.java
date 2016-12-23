package com.std.account.domain;

import java.util.Date;

import com.std.account.dao.base.ABaseDO;

/**
 * 账户流水单号
 * @author: xieyj 
 * @since: 2016年11月10日 下午5:48:27 
 * @history:
 */
public class Jour extends ABaseDO {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 1255747682967604091L;

    // 流水编号
    private String code;

    // 用户编号
    private String userId;

    // 真实姓名
    private String realName;

    // 账号
    private String accountNumber;

    // 渠道类型
    private String channelType;

    // 渠道单号
    private String channelOrder;

    // 业务类型
    private String bizType;

    // 业务说明
    private String bizNote;

    // 变动金额，
    private Long transAmount;

    // 变动前金额，
    private Long preAmount;

    // 变动后金额，
    private Long postAmount;

    // 状态（生成待回调，无需对账，已回调待对账，对账通过，对账不通过待调账，已调账）
    private String status;

    // 创建时间
    private Date createDatetime;

    // 处理回调人
    private String rollbackUser;

    // 处理回调时间
    private String rollbackDatetime;

    // 拟对账时间
    private String workDate;

    // 对账人
    private String checkUser;

    // 对账时间
    private Date checkDatetime;

    // 调账人
    private String adjustUser;

    // 调账时间
    private Date adjustDatetime;

    // 备注
    private String remark;

    // 系统编号
    private String systemCode;

    // ***********************db properties *************************

    // 查询条件1：创建起始时间
    private Date createDatetimeStart;

    // 查询条件2：创建终止时间
    private Date createDatetimeEnd;

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

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getChannelOrder() {
        return channelOrder;
    }

    public void setChannelOrder(String channelOrder) {
        this.channelOrder = channelOrder;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getBizNote() {
        return bizNote;
    }

    public void setBizNote(String bizNote) {
        this.bizNote = bizNote;
    }

    public Long getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(Long transAmount) {
        this.transAmount = transAmount;
    }

    public Long getPreAmount() {
        return preAmount;
    }

    public void setPreAmount(Long preAmount) {
        this.preAmount = preAmount;
    }

    public Long getPostAmount() {
        return postAmount;
    }

    public void setPostAmount(Long postAmount) {
        this.postAmount = postAmount;
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

    public String getRollbackUser() {
        return rollbackUser;
    }

    public void setRollbackUser(String rollbackUser) {
        this.rollbackUser = rollbackUser;
    }

    public String getRollbackDatetime() {
        return rollbackDatetime;
    }

    public void setRollbackDatetime(String rollbackDatetime) {
        this.rollbackDatetime = rollbackDatetime;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }

    public Date getCheckDatetime() {
        return checkDatetime;
    }

    public void setCheckDatetime(Date checkDatetime) {
        this.checkDatetime = checkDatetime;
    }

    public String getAdjustUser() {
        return adjustUser;
    }

    public void setAdjustUser(String adjustUser) {
        this.adjustUser = adjustUser;
    }

    public Date getAdjustDatetime() {
        return adjustDatetime;
    }

    public void setAdjustDatetime(Date adjustDatetime) {
        this.adjustDatetime = adjustDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
