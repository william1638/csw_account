package com.std.account.dto.req;

import java.util.List;

public class XN802510Req {
    // 系统编号
    private String systemCode;

    // 银行卡号
    private String bankcardNumber;

    // 发生金额
    private String transAmount;

    // 账户编号
    private String accountNumber;

    // 业务类型
    private String bizType;

    // 业务备注
    private String bizNote;

    // 渠道类型列表
    private List<String> channelTypeList;

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getBankcardNumber() {
        return bankcardNumber;
    }

    public void setBankcardNumber(String bankcardNumber) {
        this.bankcardNumber = bankcardNumber;
    }

    public String getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(String transAmount) {
        this.transAmount = transAmount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    public List<String> getChannelTypeList() {
        return channelTypeList;
    }

    public void setChannelTypeList(List<String> channelTypeList) {
        this.channelTypeList = channelTypeList;
    }
}
