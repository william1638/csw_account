package com.std.account.dto.req;

public class XN802000Req {
    // 通道编号
    private String channelNo;

    // 通道状态：1可用；0不可用
    private String channelStatus;

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getChannelStatus() {
        return channelStatus;
    }

    public void setChannelStatus(String channelStatus) {
        this.channelStatus = channelStatus;
    }

}
