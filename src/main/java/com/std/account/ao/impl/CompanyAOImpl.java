package com.std.account.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.account.ao.ICompanyAO;
import com.std.account.bo.ICompanyBO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.Company;

/** 
 * 企业列表
 * @author: wuql
 * @since: 2016年1月14日 下午8:43:33 
 * @history:
 */
@Service
public class CompanyAOImpl implements ICompanyAO {
    @Autowired
    ICompanyBO companyBO;

    @Override
    public Paginable<Company> queryCompanyPage(int start, int limit,
            Company condition) {
        return companyBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Company> queryCompanyList(Company condition) {
        return companyBO.queryCompanyList(condition);
    }
}
