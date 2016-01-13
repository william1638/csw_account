/**
 * @Title UserLockBOImpl.java 
 * @Package com.ibis.pz.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-7 上午9:20:54 
 * @version V1.0   
 */
package com.std.account.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.IUserLockBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.dao.IUserLockDAO;
import com.std.account.domain.UserLock;

/** 
 * @author: miyb 
 * @since: 2015-3-7 上午9:20:54 
 * @history:
 */
@Component
public class UserLockBOImpl extends PaginableBOImpl<UserLock> implements
        IUserLockBO {
    @Autowired
    private IUserLockDAO userLockDAO;

    /** 
     * @see com.ibis.pz.user.IUserLockBO#saveUserLock(com.UserLock.pz.domain.UserLockDO)
     */
    @Override
    public int saveUserLock(UserLock data) {
        int count = 0;
        if (data != null) {
            count = userLockDAO.insert(data);
        }
        return count;
    }

    /** 
     * @see com.ibis.pz.user.IUserLockBO#refreshLockDirection(com.UserLock.pz.domain.UserLockDO)
     */
    @Override
    public int refreshLockDirection(UserLock data) {
        int count = 0;
        if (data != null && data.getId() > 0) {
            count = userLockDAO.updateLockDirection(data);
        }
        return count;
    }

    /** 
     * @see com.ibis.pz.user.IUserLockBO#getUserLock(java.lang.Long)
     */
    @Override
    public UserLock getUserLock(Long id) {
        UserLock data = null;
        if (id > 0) {
            UserLock condition = new UserLock();
            condition.setId(id);
            data = userLockDAO.select(condition);
        }
        return data;
    }

    /** 
     * @see com.ibis.pz.user.IUserLockBO#queryUserLockList(com.UserLock.pz.domain.UserLockDO)
     */
    @Override
    public List<UserLock> queryUserLockList(UserLock data) {
        return userLockDAO.selectList(data);
    }

}
