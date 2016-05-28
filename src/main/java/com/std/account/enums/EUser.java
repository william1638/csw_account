package com.std.account.enums;

public enum EUser {
    // li表示程序
    LI("li", "程序");

    EUser(String code, String value) {
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
