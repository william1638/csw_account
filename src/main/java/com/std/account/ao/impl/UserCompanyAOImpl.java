package com.std.account.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.account.ao.IUserCompanyAO;
import com.std.account.bo.ICompanyBO;
import com.std.account.bo.IUserCompanyBO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.Company;
import com.std.account.domain.UserCompany;
import com.std.account.exception.BizException;

/**
 * 用户企业关联表
 * @author: wuql
 * @since: 2016年1月15日 上午11:39:56 
 * @history:
 */
@Service
public class UserCompanyAOImpl implements IUserCompanyAO {
    @Autowired
    IUserCompanyBO userCompanyBO;

    @Autowired
    ICompanyBO companyBO;

    /**
     * @see com.std.account.ao.IUserCompanyAO#addUserCompany(com.std.account.domain.UserCompany)
     */
    @Override
    public String addUserCompany(UserCompany data) {
        if (data != null) {
            if (!userCompanyBO.isUserCompanyExist(data.getUserId(),
                data.getCompanyId())) {
                userCompanyBO.saveUserCompany(data);
            } else {
                throw new BizException("li01006", "用户企业关联已经存在！");
            }
        }
        return data.getUserId();
    }

    @Override
    public boolean dropUserCompany(Long id) {
        if (!userCompanyBO.isUserCompanyIdExist(id)) {
            throw new BizException("xn702000", "用户企业关联编号不存在！");
        }
        userCompanyBO.removeUserCompany(id);
        return true;
    }

    /**
     * @see com.std.account.ao.IUserCompanyAO#editUserCompany(com.std.account.domain.UserCompany)
     */
    @Override
    public boolean editUserCompany(UserCompany data) {
        if (data != null) {
            if (!userCompanyBO.isUserCompanyIdExist(data.getId())) {
                throw new BizException("xn000001", "用户企业关联编号不存在！");
            }
            if (userCompanyBO.isUserCompanyExist(data.getUserId(),
                data.getCompanyId())) {
                throw new BizException("xn000001", "用户企业关联已存在！");
            }
            userCompanyBO.refreshUserCompany(data);
        }
        return true;
    }

    /** 
     * @see com.ibis.account.ao.IUserCompanyAO#queryUserCompanyPage(int, int, com.ibis.account.domain.UserCompany)
     */
    @Override
    public Paginable<UserCompany> queryUserCompanyPage(int start, int limit,
            UserCompany condition) {
        return userCompanyBO.getPaginable(start, limit, condition);
    }

    /** 
     * @see com.ibis.account.ao.IUserCompanyAO#queryUserCompanyList(com.ibis.account.domain.UserCompany)
     */
    @Override
    public List<UserCompany> queryUserCompanyList(UserCompany condition) {
        return userCompanyBO.queryUserCompanyList(condition);
    }

    /**
     * @see com.std.account.ao.IUserCompanyAO#queryUserCompanyListByUserId(java.lang.String)
     */
    public List<Company> queryUserCompanyListByUserId(String userId) {
        UserCompany condition = new UserCompany();
        condition.setUserId(userId);

        List<Company> companyList = new ArrayList<Company>();
        List<String> companyIdList = new ArrayList<String>();
        List<UserCompany> userCompanyList = new ArrayList<UserCompany>();
        userCompanyList = userCompanyBO.queryUserCompanyList(condition);

        if (userCompanyList != null && userCompanyList.size() > 0) {
            for (UserCompany userCompany : userCompanyList) {
                companyIdList.add(userCompany.getCompanyId());
            }
        }
        if (companyIdList != null && companyIdList.size() > 0) {
            for (String companyId : companyIdList) {
                companyList.add(companyBO.getCompany(companyId));
            }
        }
        return companyList;
    }

}
