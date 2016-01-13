/**
 * @Title XN702515Req.java 
 * @Package com.xnjr.account.dto.req 
 * @Description 
 * @author haiqingzheng  
 * @date 2015年11月17日 下午1:42:36 
 * @version V1.0   
 */
package com.std.account.dto.req;

/** 
 * @author: haiqingzheng 
 * @since: 2015年11月17日 下午1:42:36 
 * @history:
 */
public class XN702515Req {

    // 充值金额（必填）（精确到厘）
    private String amount;

    // 账户编号（必填）
    private String accountNumber;

    // 充值手续费（精确到厘）--必填,但可以为0
    private String fee;

    // 用户登录IP
    private String userIp;

    // 身份证号码
    private String idNo;

    // 用户userId
    private String userId;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
