package com.std.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.account.dao.ICompanyDAO;
import com.std.account.dao.base.support.AMybatisTemplate;
import com.std.account.domain.Company;

/** 
 * 公司列表
 * @author: wuql
 * @since: 2016年1月14日 下午2:26:27 
 * @history:
 */
@Repository("companyDAOImpl")
public class CompanyDAOImpl extends AMybatisTemplate implements ICompanyDAO {

    /**
     * @see com.std.account.dao.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(Company data) {
        return super.insert("insert_company", data);
    }

    /** 
     * @see com.std.account.dao.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(Company data) {
        return super.delete("delete_company", data);
    }

    /**
     * @see com.std.account.dao.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public Company select(Company condition) {
        return super.select("select_company", condition, Company.class);
    }

    /**
     * @see com.std.account.dao.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public long selectTotalCount(Company condition) {
        return super.selectTotalCount("select_company_count", condition);
    }

    /**
     * @see com.std.account.dao.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<Company> selectList(Company condition) {
        return super.selectList("select_company", condition, Company.class);
    }

    /**
     * @see com.std.account.dao.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<Company> selectList(Company condition, int start, int count) {
        return super.selectList("select_company", start, count, condition,
            Company.class);
    }

    /**
     * @see com.std.account.dao.ICompanyDAO#update(com.std.account.domain.Company)
     */
    @Override
    public int update(Company data) {
        return super.update("update_company", data);
    }
}
