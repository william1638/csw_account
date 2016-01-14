package com.std.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.account.dao.IUserIdentifyDAO;
import com.std.account.dao.base.support.AMybatisTemplate;
import com.std.account.domain.UserIdentify;

/**
d * @author: luoqi 
 * @since: 2015年3月7日 下午6:37:56 
 * @history:
 */
@Repository("userIdentifyDAOImpl")
public class UserIdentifyDAOImpl extends AMybatisTemplate implements
        IUserIdentifyDAO {

    /**
     * @see com.ibis.pz.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(UserIdentify data) {
        return super.insert(NAMESPACE.concat("insert_userIdentify"), data);
    }

    /**
     * @see com.ibis.pz.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(UserIdentify data) {
        return 0;
    }

    /**
     * @see com.ibis.pz.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public UserIdentify select(UserIdentify condition) {
        return super.select(NAMESPACE.concat("select_userIdentify"), condition,
            UserIdentify.class);
    }

    /**
     * @see com.ibis.pz.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public long selectTotalCount(UserIdentify condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_userIdentify_count"), condition);
    }

    /**
     * @see com.ibis.pz.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<UserIdentify> selectList(UserIdentify condition) {
        return super.selectList(NAMESPACE.concat("select_userIdentify"),
            condition, UserIdentify.class);
    }

    /**
     * @see com.ibis.pz.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<UserIdentify> selectList(UserIdentify condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_userIdentify"), start,
            count, condition, UserIdentify.class);
    }

}
