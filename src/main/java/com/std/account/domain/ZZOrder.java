/**
 * @Title TransferJour.java 
 * @Package com.ibis.account.domain 
 * @Description 
 * @author miyb  
 * @date 2015-2-12 下午8:39:50 
 * @version V1.0   
 */
package com.std.account.domain;

import java.util.Date;

import com.std.account.dao.base.ABaseDO;

/** 
 * @author: miyb 
 * @since: 2015-2-12 下午8:39:50 
 * @history:
 */
public class ZZOrder extends ABaseDO {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -4526289105856827077L;

    // 查询条件1：创建起始时间
    private Date createDatetimeStart;

    // 查询条件2：创建终止时间
    private Date createDatetimeEnd;

    // ---------db properties start--------------------------
    // 流水号
    private String zzNo;

    // 状态
    private String status;

    // 方向：1=转入；0=转出
    private String direction;

    // 金额（精确到厘）
    private Long amount;

    // 创建时间
    private Date createDatetime;

    // 对方系统
    private String oppositeSystem;

    // 对方账号
    private String oppositeAccount;

    // 备注
    private String remark;

    // 账号
    private String accountNumber;

    public String getZzNo() {
        return zzNo;
    }

    public void setZzNo(String zzNo) {
        this.zzNo = zzNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getOppositeSystem() {
        return oppositeSystem;
    }

    public void setOppositeSystem(String oppositeSystem) {
        this.oppositeSystem = oppositeSystem;
    }

    public String getOppositeAccount() {
        return oppositeAccount;
    }

    public void setOppositeAccount(String oppositeAccount) {
        this.oppositeAccount = oppositeAccount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

}
