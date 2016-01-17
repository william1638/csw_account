package com.std.account.bo;

import java.util.List;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.Company;
import com.std.account.domain.User;
import com.std.account.domain.UserCompany;

/** 
 * 用户企业关联表
 * @author: wuql
 * @since: 2016年1月15日 上午11:23:46 
 * @history:
 */
public interface IUserCompanyBO extends IPaginableBO<UserCompany> {

    public boolean isUserCompanyExist(String userId, String companyId);

    public int removeUserCompany(String userId, String companyId);

    public void saveUserCompany(String userId, String companyId, String remark);

    public List<User> queryUserList(String companyId);

    public List<Company> queryCompanyList(String userId);
}
