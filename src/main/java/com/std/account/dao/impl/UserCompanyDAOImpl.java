package com.std.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.account.dao.IUserCompanyDAO;
import com.std.account.dao.base.support.AMybatisTemplate;
import com.std.account.domain.UserCompany;

/** 
 * 用户企业关联DAO
 * @author: wuql
 * @since: 2016年1月15日 上午11:10:29 
 * @history:
 */
@Repository("userCompanyDAOImpl")
public class UserCompanyDAOImpl extends AMybatisTemplate implements
        IUserCompanyDAO {

    /**
     * @see com.std.account.dao.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(UserCompany data) {
        return super.insert(NAMESPACE.concat("insert_userCompany"), data);
    }

    /**
     * @see com.std.account.dao.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(UserCompany data) {
        return super.delete(NAMESPACE.concat("delete_userCompany"), data);
    }

    /**
     * @see com.std.account.dao.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public UserCompany select(UserCompany condition) {
        return (UserCompany) super.select(
            NAMESPACE.concat("select_userCompany"), condition,
            UserCompany.class);
    }

    /** 
     * @see com.std.account.dao.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public long selectTotalCount(UserCompany condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_userCompany_count"), condition);
    }

    /** 
     * @see com.std.account.dao.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<UserCompany> selectList(UserCompany condition) {
        return super.selectList(NAMESPACE.concat("select_userCompany"),
            condition, UserCompany.class);
    }

    /** 
     * @see com.std.account.dao.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<UserCompany> selectList(UserCompany condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_userCompany"), start,
            count, condition, UserCompany.class);
    }

}
