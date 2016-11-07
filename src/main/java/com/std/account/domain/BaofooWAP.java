package com.std.account.domain;

public class BaofooWAP {
    // 版本号
    private String version = "4.0.0.1";

    // 字符集
    private String inputCharset = "UTF-8";

    private String language = "1";

    private String txnType = "03311";

    private String txnSubType = "01";

    private String bizType;

    private String pfxPath = "cer/bfkey_100000178@@100000916.pfx";

    private String pfxPwd = "100000178_204500";

    private String cerPath = "/cer/baofoo_pub_test.cer";

    // 数据类型
    private String dataType = "json";

    // 渠道访问地址
    private String payUrl = "https://gw.baofoo.com/apipay/sdk";

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getInputCharset() {
        return inputCharset;
    }

    public void setInputCharset(String inputCharset) {
        this.inputCharset = inputCharset;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public String getTxnSubType() {
        return txnSubType;
    }

    public void setTxnSubType(String txnSubType) {
        this.txnSubType = txnSubType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getPfxPath() {
        return pfxPath;
    }

    public void setPfxPath(String pfxPath) {
        this.pfxPath = pfxPath;
    }

    public String getPfxPwd() {
        return pfxPwd;
    }

    public void setPfxPwd(String pfxPwd) {
        this.pfxPwd = pfxPwd;
    }

    public String getCerPath() {
        return cerPath;
    }

    public void setCerPath(String cerPath) {
        this.cerPath = cerPath;
    }

}
