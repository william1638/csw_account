package com.std.account.enums;

public enum EPayType {
    PC("PC", "PC端网关支付"), WAP("WAP", "WAP一键支付"), CZB("01", "橙账本");

    EPayType(String code, String value) {
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
