/**
 * @Title UserDAOImpl.java 
 * @Package com.ibis.pz.impl 
 * @Description 
 * @author miyb  
 * @date 2015-2-6 上午10:22:53 
 * @version V1.0   
 */
package com.std.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.account.dao.IUserDAO;
import com.std.account.dao.base.support.AMybatisTemplate;
import com.std.account.domain.User;

/** 
 * @author: miyb 
 * @since: 2015-2-6 上午10:22:53 
 * @history:
 */
@Repository("userDAOImpl")
public class UserDAOImpl extends AMybatisTemplate implements IUserDAO {

    /** 
     * @see com.ibis.pz.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(User data) {
        return super.insert(NAMESPACE.concat("insert_user"), data);
    }

    /** 
     * @see com.ibis.pz.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(User data) {
        return super.delete(NAMESPACE.concat("delete_user"), data);
    }

    /** 
     * @see com.ibis.pz.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public User select(User condition) {
        return super.select(NAMESPACE.concat("select_user"), condition,
            User.class);
    }

    /** 
     * @see com.ibis.pz.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public long selectTotalCount(User condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_user_count"),
            condition);
    }

    /** 
     * @see com.ibis.pz.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<User> selectList(User condition) {
        return super.selectList(NAMESPACE.concat("select_user"), condition,
            User.class);
    }

    /** 
     * @see com.ibis.pz.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<User> selectList(User condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_user"), start, count,
            condition, User.class);
    }

    /** 
     * @see com.xnjr.account.dao.ibis.pz.IUserDAO#updateIdentity(com.User.pz.domain.UserDO)
     */
    @Override
    public int updateIdentity(User data) {
        return super.update(NAMESPACE.concat("update_identity"), data);
    }

    /** 
     * @see com.xnjr.account.dao.ibis.pz.IUserDAO#updateTradePwd(com.User.pz.domain.UserDO)
     */
    @Override
    public int updateTradePwd(User data) {
        return super.update(NAMESPACE.concat("update_trade_pwd"), data);
    }

    /** 
     * @see com.xnjr.account.dao.ibis.pz.IUserDAO#updateLoginPwd(com.User.pz.domain.UserDO)
     */
    @Override
    public int updateLoginPwd(User data) {
        return super.update(NAMESPACE.concat("update_login_pwd"), data);
    }

    /** 
     * @see com.xnjr.account.dao.ibis.pz.IUserDAO#updateLoginPwd(com.User.pz.domain.UserDO)
     */
    @Override
    public int updateMobile(User data) {
        return super.update(NAMESPACE.concat("update_mobile"), data);
    }

    @Override
    public int insertFaRen(User user) {
        return super.insert(NAMESPACE.concat("insert_faren"), user);
    }

}
