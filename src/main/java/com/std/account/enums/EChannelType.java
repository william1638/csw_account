package com.std.account.enums;

public enum EChannelType {
    Yeepay("11", "线上-易宝支付"), Baofoo("12", "线上-宝付支付"), Fuiou("13", "富友支付"), OFFLINE(
            "01", "线下");

    EChannelType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

}
