package com.std.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.account.dao.IUserExtDAO;
import com.std.account.dao.base.support.AMybatisTemplate;
import com.std.account.domain.UserExt;

/**
 * @author: luoqi 
 * @since: 2015年3月7日 下午6:30:51 
 * @history:
 */
@Repository("userExtDAOImpl")
public class UserExtDAOImpl extends AMybatisTemplate implements IUserExtDAO {

    /**
     * @see com.ibis.pz.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(UserExt data) {
        return super.insert(NAMESPACE.concat("insert_userExt"), data);
    }

    /**
     * @see com.ibis.pz.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(UserExt data) {
        return super.delete(NAMESPACE.concat("delete_userExt"), data);
    }

    /**
     * @see com.ibis.pz.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public UserExt select(UserExt condition) {
        return super.select(NAMESPACE.concat("select_userExt"), condition,
            UserExt.class);
    }

    /**
     * @see com.ibis.pz.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public long selectTotalCount(UserExt condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_userExt_count"),
            condition);
    }

    /**
     * @see com.ibis.pz.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<UserExt> selectList(UserExt condition) {
        return super.selectList(NAMESPACE.concat("select_userExt"), condition,
            UserExt.class);
    }

    /**
     * @see com.ibis.pz.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<UserExt> selectList(UserExt condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_userExt"), start,
            count, condition, UserExt.class);
    }

    /**
     * @see com.ibis.pz.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public int update(UserExt data) {
        return super.update(NAMESPACE.concat("update_userExt"), data);
    }

    @Override
    public int updatePhoto(UserExt data) {
        return super.update(NAMESPACE.concat("update_photo"), data);
    }

}
