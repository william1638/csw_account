package com.std.account.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.IUserLoginLogBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.dao.IUserLoginLogDAO;
import com.std.account.domain.UserLoginLog;

/**
 * 用户登录日志
 * @author: xieyj 
 * @since: 2015-3-10 下午8:11:38 
 * @history:
 */
@Component
public class UserLoginLogBOImpl extends PaginableBOImpl<UserLoginLog> implements
        IUserLoginLogBO {

    @Autowired
    private IUserLoginLogDAO userLoginLogDAO;

    @Override
    public int saveUserLoginLogBO(String userId, String loginIp,
            String loginStatus) {
        UserLoginLog data = new UserLoginLog();
        data.setUserId(userId);
        data.setLoginIp(loginIp);
        data.setLoginDatetime(new Date());
        data.setIsSuccess(loginStatus);
        int count = 0;
        if (data != null) {
            count = userLoginLogDAO.insert(data);
        }
        return count;
    }

    @Override
    public List<UserLoginLog> queryUserLoginLogBOList(UserLoginLog data) {
        return userLoginLogDAO.selectList(data);
    }

    @Override
    public UserLoginLog getLatestUserLoginLog(String userId) {
        UserLoginLog data = null;
        if (StringUtils.isNotBlank(userId)) {
            UserLoginLog condition = new UserLoginLog();
            condition.setUserId(userId);
            data = userLoginLogDAO.selectLatestUserLoginLog(condition);
        }
        return data;
    }

}
