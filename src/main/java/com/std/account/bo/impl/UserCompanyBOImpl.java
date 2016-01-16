package com.std.account.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.IUserCompanyBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.dao.IUserCompanyDAO;
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
    public UserCompany getUserCompany(Long id) {
        UserCompany data = null;
        if (id > 0) {
            UserCompany condition = new UserCompany();
            condition.setId(id);
            data = userCompanyDAO.select(condition);
        }
        return data;
    }

    /**
     * @see com.std.account.bo.IUserCompanyBO#queryUserCompanyList(com.std.account.domain.UserCompany)
     */
    @Override
    public List<UserCompany> queryUserCompanyList(UserCompany data) {
        return userCompanyDAO.selectList(data);
    }

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

}
