/**
 * @Title ICompanyAO.java 
 * @Package com.xnjr.cpzc.system 
 * @Description 
 * @author xieyj  
 * @date 2015年9月10日 上午8:50:17 
 * @version V1.0   
 */
package com.std.account.ao;

import java.util.List;

import com.std.account.annotation.ServiceModule;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.Company;

/** 
 * @author: wuql
 * @since: 2016年1月14日 下午8:40:16 
 * @history:
 */
@ServiceModule
public interface ICompanyAO {
    String DEFAULT_ORDER_COLUMN = "company_id";

    /**
     * 分页查询企业列表
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2016年1月14日 下午8:41:56 wuql
     * @history:
     */
    public Paginable<Company> queryCompanyPage(int start, int limit,
            Company condition);

    /**
     * 查询企业列表
     * @param condition
     * @return 
     * @create: 2016年1月14日 下午8:42:15 wuql
     * @history:
     */
    public List<Company> queryCompanyList(Company condition);

    /**
     * 查询企业详情
     * @param companyId
     * @return 
     * @create: 2016年1月15日 上午10:11:58 wuql
     * @history:
     */
    public Company doGetCompany(String companyId);
}
