package com.std.account.dto.req;

public class XN702703Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 1293923752912799376L;

    // 订单号
    private String xnbNo;

    // 是否已经审核（1待审批;2审批通过；3审批不通过；9不需要审批直接转化成账户金额）
    private String status;

    // 积分种类
    private String type;

    // 审批人（li为程序）
    private String approveUser;

    // 账号
    private String accountNumber;

    public String getXnbNo() {
        return xnbNo;
    }

    public void setXnbNo(String xnbNo) {
        this.xnbNo = xnbNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getApproveUser() {
        return approveUser;
    }

    public void setApproveUser(String approveUser) {
        this.approveUser = approveUser;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

}
