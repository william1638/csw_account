package com.std.account.bo;

import java.util.List;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.UserLoginLog;

/**
 * 用户登录日志
 * @author: xieyj 
 * @since: 2015-3-10 下午8:11:38 
 * @history:
 */
public interface IUserLoginLogBO extends IPaginableBO<UserLoginLog> {

    public UserLoginLog getLatestUserLoginLog(String userId);

    public List<UserLoginLog> queryUserLoginLogBOList(UserLoginLog data);

    public int saveUserLoginLogBO(String userId, String loginIp, String code);

}
