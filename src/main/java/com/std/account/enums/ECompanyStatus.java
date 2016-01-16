package com.std.account.enums;

public enum ECompanyStatus {
    todoKYC("1", "待kyc"), KYC_YES("2", "kyc通过"), KYC_NO("3", "kyc不通过");
    ECompanyStatus(String code, String value) {
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
