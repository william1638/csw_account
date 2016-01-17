package com.std.account.dao;

import java.util.List;

import com.std.account.dao.base.IBaseDAO;
import com.std.account.domain.Company;
import com.std.account.domain.User;
import com.std.account.domain.UserCompany;

/** 
 * 用户企业关联表
 * @author: wuql
 * @since: 2016年1月15日 上午11:00:25 
 * @history:
 */
public interface IUserCompanyDAO extends IBaseDAO<UserCompany> {
    String NAMESPACE = IUserCompanyDAO.class.getName().concat(".");

    List<Company> selectCompanyList(UserCompany condition);

    List<User> selectUserList(UserCompany condition);

}
