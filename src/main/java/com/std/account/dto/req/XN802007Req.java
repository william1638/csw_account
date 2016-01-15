package com.std.account.dto.req;

public class XN802007Req {
    // 银行编号：采用银行缩写
    private String bankNo;

    // 银行名称
    private String bankName;

    // 银行类别：1地区银行；2非地区银行
    private String bankType;

    // 是否快捷支付：1是0否
    private String quickType;

    // 是否启用：1是0否
    private String isEnable;

    // 隶属于支付通道
    private String channelNo;

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getQuickType() {
        return quickType;
    }

    public void setQuickType(String quickType) {
        this.quickType = quickType;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

}
