package com.std.account.dao;

import com.std.account.dao.base.IBaseDAO;
import com.std.account.domain.UserIdentify;

/**
 * @author: luoqi 
 * @since: 2015年3月7日 下午6:26:49 
 * @history:
 */
public interface IUserIdentifyDAO extends IBaseDAO<UserIdentify> {
    String NAMESPACE = IUserIdentifyDAO.class.getName().concat(".");
}
