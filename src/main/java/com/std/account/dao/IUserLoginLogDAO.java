package com.std.account.dao;

import com.std.account.dao.base.IBaseDAO;
import com.std.account.domain.UserLoginLog;

/**
 * 
 * @author: luoqi 
 * @since: 2015年3月7日 下午1:56:37 
 * @history:
 */
public interface IUserLoginLogDAO extends IBaseDAO<UserLoginLog> {
    String NAMESPACE = IUserLoginLogDAO.class.getName().concat(".");

    public UserLoginLog selectLatestUserLoginLog(UserLoginLog condition);
}
