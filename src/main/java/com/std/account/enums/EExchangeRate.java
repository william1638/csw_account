package com.std.account.enums;

public enum EExchangeRate {
    CNY2CGB("CNY2CGB", "1人民币=多少菜狗币"), CNY2GXZ("CNY2GXZ", "1人民币=多少贡献值"), CNY2ZHFB(
            "CNY2ZHFB", "1人民币=多少分润币");

    EExchangeRate(String code, String value) {
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
