package com.std.account.dao;

import com.std.account.dao.base.IBaseDAO;
import com.std.account.domain.Company;

/** 
 * 公司列表DAO
 * @author: wuql
 * @since: 2016年1月14日 下午2:26:01 
 * @history:
 */
public interface ICompanyDAO extends IBaseDAO<Company> {
    String NAMESPACE = ICompanyDAO.class.getName().concat(".");

    public int updatePicture(Company data);

    public int updateCompany(Company data);
}
