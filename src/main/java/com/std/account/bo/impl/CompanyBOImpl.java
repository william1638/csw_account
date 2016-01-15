package com.std.account.bo.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.ICompanyBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.dao.ICompanyDAO;
import com.std.account.domain.Company;
import com.std.account.exception.BizException;

/** 
 * 公司列表
 * @author: wuql
 * @since: 2016年1月14日 下午2:42:31 
 * @history:
 */
@Component
public class CompanyBOImpl extends PaginableBOImpl<Company>
        implements ICompanyBO {
    @Autowired
    private ICompanyDAO companyDAO;

    @Override
    public boolean isCompanyExist(String companyId) {
        Company company = new Company();
        company.setCompanyId(companyId);
        if (companyDAO.selectTotalCount(company) == 1) {
            return true;
        }
        return false;
    }

    /** 
     * @see com.std.account.bo.ICompanyBO#saveCompany(com.std.account.domain.Company)
     */
    @Override
    public int saveCompany(Company data) {
        int count = 0;
        if (data != null) {
            data.setApplyDatetime(new Date());
            count = companyDAO.insert(data);
        }
        return count;
    }

    /**
     * @see com.std.account.bo.ICompanyBO#removeCompany(java.lang.String)
     */
    @Override
    public int removeCompany(String companyId) {
        int count = 0;
        if (companyId != null) {
            Company data = new Company();
            data.setCompanyId(companyId);
            count = companyDAO.delete(data);
        }
        return count;
    }

    /**
     * @see com.std.account.bo.ICompanyBO#refreshCompany(com.std.account.domain.Company)
     */
    @Override
    public int refreshCompany(Company data) {
        int count = 0;
        if (data != null) {
            data.setApplyDatetime(new Date());
            count = companyDAO.update(data);
        }
        return count;
    }

    /**
     * @see com.std.account.bo.ICompanyBO#getCompany(java.lang.String)
     */
    @Override
    public Company getCompany(String companyId) {
        Company condition = new Company();
        Company companyDO = null;
        if (companyId != null) {
            condition.setCompanyId(companyId);
            companyDO = companyDAO.select(condition);
        }
        if (companyDO != null) {
            return companyDO;
        }
        throw new BizException("xn000001", "公司信息不存在！");
    }

    /**
     * @see com.std.account.bo.ICompanyBO#queryCompanyList(com.std.account.domain.Company)
     */
    @Override
    public List<Company> queryCompanyList(Company condition) {
        return companyDAO.selectList(condition);
    }
}
