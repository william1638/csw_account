package com.std.account.domain;

import java.util.Date;

import com.std.account.dao.base.ABaseDO;

/**
 * 
 * @author: luoqi 
 * @since: 2015年3月7日 下午1:47:51 
 * @history:
 */
public class UserLoginLog extends ABaseDO {
    // 登录时间
    private Date loginDatetimeStart;

    private Date loginDatetimeEnd;

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 3885676331810740357L;

    // user_id
    private String userId;

    // 登录时间
    private Date loginDatetime;

    // 登录IP地址
    private String loginIp;

    // 是否登录成功
    private String isSuccess;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getLoginDatetime() {
        return loginDatetime;
    }

    public void setLoginDatetime(Date loginDatetime) {
        this.loginDatetime = loginDatetime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Date getLoginDatetimeStart() {
        return loginDatetimeStart;
    }

    public void setLoginDatetimeStart(Date loginDatetimeStart) {
        this.loginDatetimeStart = loginDatetimeStart;
    }

    public Date getLoginDatetimeEnd() {
        return loginDatetimeEnd;
    }

    public void setLoginDatetimeEnd(Date loginDatetimeEnd) {
        this.loginDatetimeEnd = loginDatetimeEnd;
    }

    @Override
    public String toString() {
        return "UserLoginLogDO [userId=" + userId + ", loginDatetime="
                + loginDatetime + ", loginIp=" + loginIp + ", isSuccess="
                + isSuccess + "]";
    }

}
