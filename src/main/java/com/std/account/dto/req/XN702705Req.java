package com.std.account.dto.req;

public class XN702705Req extends APageReq {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 3469529018797225297L;

    // 订单号
    private String hlNo;

    // 状态
    private String status;

    // 方向：1=蓝补；0=红冲
    private String direction;

    // 申请人
    private String applyUser;

    // 审批人（li为程序）
    private String approveUser;

    // 账号
    private String accountNumber;

    // 手机号
    private String mobile;

    // 真实姓名
    private String realName;

    public String getHlNo() {
        return hlNo;
    }

    public void setHlNo(String hlNo) {
        this.hlNo = hlNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
