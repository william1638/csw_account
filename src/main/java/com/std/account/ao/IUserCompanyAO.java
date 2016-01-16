package com.std.account.ao;

import java.util.List;

import com.std.account.annotation.ServiceModule;
import com.std.account.domain.Company;

/** 
 * 用户企业关联表
 * @author: wuql
 * @since: 2016年1月15日 上午11:34:20 
 * @history:
 */
@ServiceModule
public interface IUserCompanyAO {
    String DEFAULT_ORDER_COLUMN = "id";

    /**
     * 根据用户名查询企业列表
     * @param condition
     * @return 
     * @create: 2016年1月15日 上午11:37:27 wuql
     * @history:
     */
    public List<Company> queryUserCompanyListByUserId(String userId);

    public void doBindUserCompany(String userId, String companyId, String remark);

    public void doUnbindUserCompany(String userId, String companyId);
}
