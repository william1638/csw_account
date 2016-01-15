package com.std.account.dao;

import com.std.account.dao.base.IBaseDAO;
import com.std.account.domain.UserCompany;

/** 
 * 用户企业关联表
 * @author: wuql
 * @since: 2016年1月15日 上午11:00:25 
 * @history:
 */
public interface IUserCompanyDAO extends IBaseDAO<UserCompany> {
    String NAMESPACE = IUserCompanyDAO.class.getName().concat(".");

    public int update(UserCompany data);

}
