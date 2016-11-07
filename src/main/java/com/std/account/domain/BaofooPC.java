package com.std.account.domain;

public class BaofooPC {
    // 版本号
    private String interfaceVersion = "4.0";

    // 加密类型
    private String keyType = "1";

    // 通知类型：固定数字1
    private String noticeType = "1";

    // 渠道访问地址
    private String payUrl = "https://gw.baofoo.com/payindex";

    public String getInterfaceVersion() {
        return interfaceVersion;
    }

    public void setInterfaceVersion(String interfaceVersion) {
        this.interfaceVersion = interfaceVersion;
    }

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

}
