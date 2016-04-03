package com.std.account.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.IUserCompanyBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.dao.IUserCompanyDAO;
import com.std.account.domain.Company;
import com.std.account.domain.User;
import com.std.account.domain.UserCompany;

/** 
 * 用户企业关联表
 * @author: wuql
 * @since: 2016年1月15日 上午11:27:15 
 * @history:
 */
@Component
public class UserCompanyBOImpl extends PaginableBOImpl<UserCompany> implements
        IUserCompanyBO {
    @Autowired
    private IUserCompanyDAO userCompanyDAO;

    @Override
    public boolean isUserCompanyExist(String userId, String companyId) {
        UserCompany condition = new UserCompany();
        condition.setUserId(userId);
        condition.setCompanyId(companyId);
        if (userCompanyDAO.selectTotalCount(condition) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public int removeUserCompany(String userId, String companyId) {
        int count = 0;
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(companyId)) {
            UserCompany data = new UserCompany();
            data.setUserId(userId);
            data.setCompanyId(companyId);
            count = userCompanyDAO.delete(data);
        }
        return count;
    }

    @Override
    public void saveUserCompany(String userId, String companyId, String remark) {
        UserCompany data = new UserCompany();
        data.setUserId(userId);
        data.setCompanyId(companyId);
        data.setRemark(remark);
        userCompanyDAO.insert(data);
    }

    @Override
    public List<User> queryUserList(String companyId) {
        List<User> list = null;
        if (StringUtils.isNotBlank(companyId)) {
            UserCompany condition = new UserCompany();
            condition.setCompanyId(companyId);
            list = userCompanyDAO.selectUserList(condition);
        }
        return list;
    }

    @Override
    public List<Company> queryCompanyList(String userId) {
        List<Company> list = null;
        if (StringUtils.isNotBlank(userId)) {
            UserCompany condition = new UserCompany();
            condition.setUserId(userId);
            list = userCompanyDAO.selectCompanyList(condition);
        }
        return list;
    }

    /** 
     * @see com.std.account.bo.IUserCompanyBO#queryCompanyList(java.lang.String, java.lang.String)
     */
    @Override
    public List<Company> queryCompanyList(String userId, String companyStatus) {
        List<Company> list = null;
        if (StringUtils.isNotBlank(userId)) {
            UserCompany condition = new UserCompany();
            condition.setUserId(userId);
            condition.setCompanyStatus(companyStatus);
            list = userCompanyDAO.selectCompanyList(condition);
        }
        return list;
    }

}
