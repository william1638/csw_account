package com.std.account.ao;

import java.util.List;

import com.std.account.annotation.ServiceModule;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.Company;
import com.std.account.domain.UserCompany;

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
     * 新增用户企业关联
     * @param data
     * @return 
     * @create: 2016年1月15日 上午11:35:32 wuql
     * @history:
     */
    public String addUserCompany(UserCompany data);

    /**
     * 删除用户企业关联
     * @param id
     * @return 
     * @create: 2016年1月15日 上午11:36:10 wuql
     * @history:
     */
    public boolean dropUserCompany(Long id);

    /**
     * 更新用户企业关联
     * @param data
     * @return 
     * @create: 2016年1月15日 上午11:36:32 wuql
     * @history:
     */
    public boolean editUserCompany(UserCompany data);

    /**
     * 分页查询用户企业关联表
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2016年1月15日 上午11:36:58 wuql
     * @history:
     */
    public Paginable<UserCompany> queryUserCompanyPage(int start, int limit,
            UserCompany condition);

    /**
     * 查询用户企业关联列表
     * @param condition
     * @return 
     * @create: 2016年1月15日 上午11:37:27 wuql
     * @history:
     */
    public List<UserCompany> queryUserCompanyList(UserCompany condition);

    /**
     * 根据用户名查询企业列表
     * @param condition
     * @return 
     * @create: 2016年1月15日 上午11:37:27 wuql
     * @history:
     */
    public List<Company> queryUserCompanyListByUserId(String userId);
}
