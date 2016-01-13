package com.std.account.dto.res;

import java.util.Date;

public class XN702202Res {
    // 用户编号
    private String userId;

    // 银行行号
    private String bankCode;

    // 银行名称
    private String bankName;

    // 银行卡编号
    private String bankCardNo;

    // 开户行
    private String subbranch;

    // 银行预留手机号
    private String bindMobile;

    // 卡状态
    private String status;

    // 创建时间
    private Date createDatetime;

    // 修改时间
    // private Date updateDatetime;
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getSubbranch() {
        return subbranch;
    }

    public void setSubbranch(String subbranch) {
        this.subbranch = subbranch;
    }

    public String getBindMobile() {
        return bindMobile;
    }

    public void setBindMobile(String bindMobile) {
        this.bindMobile = bindMobile;
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

}
