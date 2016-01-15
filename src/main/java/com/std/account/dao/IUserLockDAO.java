/**
 * @Title IUserLockDAO.java 
 * @Package com.ibis.pz 
 * @Description 
 * @author miyb  
 * @date 2015-2-9 下午2:28:30 
 * @version V1.0   
 */
package com.std.account.dao;

import com.std.account.dao.base.IBaseDAO;
import com.std.account.domain.UserLock;

/** 
 * @author: miyb 
 * @since: 2015-2-9 下午2:28:30 
 * @history:
 */
public interface IUserLockDAO extends IBaseDAO<UserLock> {
    String NAMESPACE = IUserLockDAO.class.getName().concat(".");

}
