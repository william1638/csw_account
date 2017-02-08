package com.std.account.domain;

public class FuiouPC {
    // (非必填)
    private String orderValidTime = "10m";

    // (必填)
    private String ver = "1.0.1";

    private String payUrl = "https://pay.fuiou.com/smpGate.do";

    // "http://www-1.fuiou.com:8888/wg1_run/smpGate.do";

    public String getOrderValidTime() {
        return orderValidTime;
    }

    public void setOrderValidTime(String orderValidTime) {
        this.orderValidTime = orderValidTime;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

}
