/**
 * @Title UserLockDAOImpl.java 
 * @Package com.ibis.pz.impl 
 * @Description 
 * @author miyb  
 * @date 2015-2-9 下午2:29:51 
 * @version V1.0   
 */
package com.std.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.account.dao.IUserLockDAO;
import com.std.account.dao.base.support.AMybatisTemplate;
import com.std.account.domain.UserLock;

/** 
 * @author: miyb 
 * @since: 2015-2-9 下午2:29:51 
 * @history:
 */
@Repository("userLockDAOImpl")
public class UserLockDAOImpl extends AMybatisTemplate implements IUserLockDAO {

    /** 
     * @see com.ibis.pz.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(UserLock data) {
        return super.insert(NAMESPACE.concat("insert_userLock"), data);
    }

    /** 
     * @see com.ibis.pz.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(UserLock data) {
        return 0;
    }

    /** 
     * @see com.ibis.pz.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public UserLock select(UserLock condition) {
        return super.select(NAMESPACE.concat("select_userLock"), condition,
            UserLock.class);
    }

    /** 
     * @see com.ibis.pz.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public long selectTotalCount(UserLock condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_userLock_count"), condition);
    }

    /** 
     * @see com.ibis.pz.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<UserLock> selectList(UserLock condition) {
        return super.selectList(NAMESPACE.concat("select_userLock"), condition,
            UserLock.class);
    }

    /** 
     * @see com.ibis.pz.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<UserLock> selectList(UserLock condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_userLock"), start,
            count, condition, UserLock.class);
    }

    /** 
     * @see com.xnjr.account.dao.ibis.pz.IUserLockDAO#updateLockDirection(com.UserLock.pz.domain.UserLockDO)
     */
    @Override
    public int updateLockDirection(UserLock data) {
        return super.update(NAMESPACE.concat("update_lock_direction"), data);
    }

}
