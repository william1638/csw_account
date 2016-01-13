package com.std.account.dto.req;

public class XN702604Req extends APageReq {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 6542110128738662697L;

    // 账号(必传字段，因为只能查某个账号的流水)
    private String accountNumber;

    // 订单号
    private String hlNo;

    // 状态
    private String status;

    // 方向：1=蓝补；0=红冲
    private String direction;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

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

}
