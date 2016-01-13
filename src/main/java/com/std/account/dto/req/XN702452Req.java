package com.std.account.dto.req;

public class XN702452Req {
    // 通道编号
    private String channelNo;

    // 通道名称
    private String channelName;

    // 通道状态：1可用；0不可用
    private String channelStatus;

    // 备注
    private String remark;

    private String merchantId;

    private String signType;

    private String signKey;

    private String cerPath;

    private String poundageType;

    private String channelVersion;

    private String businessWebGateway;

    private String businessWapGateway;

    private String businessFileGateway;

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelStatus() {
        return channelStatus;
    }

    public void setChannelStatus(String channelStatus) {
        this.channelStatus = channelStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSignKey() {
        return signKey;
    }

    public void setSignKey(String signKey) {
        this.signKey = signKey;
    }

    public String getCerPath() {
        return cerPath;
    }

    public void setCerPath(String cerPath) {
        this.cerPath = cerPath;
    }

    public String getPoundageType() {
        return poundageType;
    }

    public void setPoundageType(String poundageType) {
        this.poundageType = poundageType;
    }

    public String getChannelVersion() {
        return channelVersion;
    }

    public void setChannelVersion(String channelVersion) {
        this.channelVersion = channelVersion;
    }

    public String getBusinessWebGateway() {
        return businessWebGateway;
    }

    public void setBusinessWebGateway(String businessWebGateway) {
        this.businessWebGateway = businessWebGateway;
    }

    public String getBusinessWapGateway() {
        return businessWapGateway;
    }

    public void setBusinessWapGateway(String businessWapGateway) {
        this.businessWapGateway = businessWapGateway;
    }

    public String getBusinessFileGateway() {
        return businessFileGateway;
    }

    public void setBusinessFileGateway(String businessFileGateway) {
        this.businessFileGateway = businessFileGateway;
    }
}
