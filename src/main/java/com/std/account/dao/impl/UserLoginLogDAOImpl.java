package com.std.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.account.dao.IUserLoginLogDAO;
import com.std.account.dao.base.support.AMybatisTemplate;
import com.std.account.domain.UserLoginLog;

/**
 * 
 * @author: luoqi 
 * @since: 2015年3月7日 下午2:00:12 
 * @history:
 */
@Repository("userLoginLogDAOImpl")
public class UserLoginLogDAOImpl extends AMybatisTemplate implements
        IUserLoginLogDAO {

    /**
     * @see com.ibis.pz.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(UserLoginLog data) {
        return super.insert(NAMESPACE.concat("insert_userLoginLog"), data);
    }

    /** 
     * @see com.ibis.pz.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(UserLoginLog data) {
        return 0;
    }

    /**
     * @see com.ibis.pz.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public UserLoginLog select(UserLoginLog condition) {
        return super.select(NAMESPACE.concat("select_userLoginLog"), condition,
            UserLoginLog.class);
    }

    /**
     * @see com.ibis.pz.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public long selectTotalCount(UserLoginLog condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_userLoginLog_count"), condition);
    }

    /**
     * @see com.ibis.pz.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<UserLoginLog> selectList(UserLoginLog condition) {
        return super.selectList(NAMESPACE.concat("select_userLoginLog"),
            condition, UserLoginLog.class);
    }

    /**
     * @see com.ibis.pz.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<UserLoginLog> selectList(UserLoginLog condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_userLoginLog"), start,
            count, condition, UserLoginLog.class);
    }

    @Override
    public UserLoginLog selectLatestUserLoginLog(UserLoginLog condition) {
        return super.select(NAMESPACE.concat("select_latestUserLoginLog"),
            condition, UserLoginLog.class);
    }

}
