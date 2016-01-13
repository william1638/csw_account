/**
 * @Title XN702708Req.java 
 * @Package com.xnjr.account.dto.req 
 * @Description 
 * @author haiqingzheng  
 * @date 2015年12月12日 下午5:05:29 
 * @version V1.0   
 */
package com.std.account.dto.req;

/** 
 * @author: haiqingzheng 
 * @since: 2015年12月12日 下午5:05:29 
 * @history:
 */
public class XN702708Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -1032637031571504295L;

    // 账号
    private String accountNumber;

    // 状态(0正常,1程序锁定,2人工锁定)
    private String status;

    // userid
    private String userId;

    // 手机号
    private String mobile;

    // 真实姓名
    private String realName;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
