/**
 * @Title ICompanyBO.java 
 * @Package com.std.account.bo
 * @Description 
 * @author wuql  
 * @date 2016年1月14日 下午2:38:13  
 * @version V1.0   
 */
package com.std.account.bo;

import java.util.List;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.Company;

/** 
 * @author: wuql
 * @since: 2016年1月14日 下午2:38:13 
 * @history:
 */
public interface ICompanyBO extends IPaginableBO<Company> {
    public boolean isCompanyExist(String companyId);

    public int saveCompany(Company data);

    public int removeCompany(String companyId);

    public int refreshCompany(Company data);

    public Company getCompany(String companyId);

    public List<Company> queryCompanyList(Company condition);
}
