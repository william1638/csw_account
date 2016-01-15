package com.std.account.bo.impl;

import java.util.List;

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
public class UserCompanyBOImpl extends PaginableBOImpl<UserCompany>
        implements IUserCompanyBO {
    @Autowired
    private IUserCompanyDAO userCompanyDAO;

    /**
     * @see com.std.account.bo.IUserCompanyBO#saveUserCompany(com.std.account.domain.UserCompany)
     */
    @Override
    public int saveUserCompany(UserCompany data) {
        int count = 0;
        if (data != null) {
            count = userCompanyDAO.insert(data);
        }
        return count;
    }

    @Override
    public int removeUserCompany(Long id) {
        int count = 0;
        if (id > 0) {
            UserCompany data = new UserCompany();
            data.setId(id);
            count = userCompanyDAO.delete(data);
        }
        return count;
    }

    /** 
     * @see com.ibis.account.bo.IUserCompanyBO#refreshUserCompany(com.ibis.account.domain.UserCompany)
     */
    @Override
    public int refreshUserCompany(UserCompany data) {
        return userCompanyDAO.update(data);
    }

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
    public boolean isUserCompanyIdExist(Long id) {
        UserCompany condition = new UserCompany();
        condition.setId(id);
        if (userCompanyDAO.selectTotalCount(condition) == 1) {
            return true;
        }
        return false;
    }

}
