package com.std.account.dto.req;

public class XN702704Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 5628844354351952841L;

    // 流水号
    private String zzNo;

    // 状态
    private String status;

    // 方向：1=转入；0=转出
    private String direction;

    // 对方系统
    private String oppositeSystem;

    // 账号
    private String accountNumber;

    public String getZzNo() {
        return zzNo;
    }

    public void setZzNo(String zzNo) {
        this.zzNo = zzNo;
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

    public String getOppositeSystem() {
        return oppositeSystem;
    }

    public void setOppositeSystem(String oppositeSystem) {
        this.oppositeSystem = oppositeSystem;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

}
