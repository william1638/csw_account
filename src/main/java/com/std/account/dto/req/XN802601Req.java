package com.std.account.dto.req;

public class XN802601Req {
    // 公司编号(必填-保证同一体系内)
    private String companyCode;

    // 来方-户名(必填)
    private String FromAccountName;

    // 来方-账号(必填)
    private String FromAccountNumber;

    // 受方-户名(必填)
    private String ToAccountName;

    // 受方-账号(必填)
    private String ToAccountNumber;

    // 变动金额(必填-划转金额)
    private String transAmount;

    // 业务类型(必填-划转业务类型)
    private String bizType;

    // 备注(选填-划转说明)
    private String remark;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getFromAccountName() {
        return FromAccountName;
    }

    public void setFromAccountName(String fromAccountName) {
        FromAccountName = fromAccountName;
    }

    public String getFromAccountNumber() {
        return FromAccountNumber;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        FromAccountNumber = fromAccountNumber;
    }

    public String getToAccountName() {
        return ToAccountName;
    }

    public void setToAccountName(String toAccountName) {
        ToAccountName = toAccountName;
    }

    public String getToAccountNumber() {
        return ToAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        ToAccountNumber = toAccountNumber;
    }

    public String getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(String transAmount) {
        this.transAmount = transAmount;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
