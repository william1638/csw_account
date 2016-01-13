package com.std.account.cms.bo;

import java.util.List;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.cms.domain.Navigation;

public interface INavigationBO extends IPaginableBO<Navigation> {
    /**
     * 导航判断是否存在
     * @param code
     * @return 
     * @create: 2016年1月5日 下午7:14:25 xieyj
     * @history:
     */
    public boolean isNavigationExist(String code);

    /**
     * 导航新增
     * @param data
     * @return 
     * @create: 2016年1月5日 下午7:14:38 xieyj
     * @history:
     */
    public int saveNavigation(Navigation data);

    /**
     * 导航删除
     * @param code
     * @return 
     * @create: 2016年1月5日 下午7:15:06 xieyj
     * @history:
     */
    public int removeNavigation(String code);

    // 查询
    /**
     * 获取某个导航
     * @param code
     * @return 
     * @create: 2016年1月5日 下午7:15:22 xieyj
     * @history:
     */
    public Navigation getNavigation(String code);

    /**
     * 导航列表查询
     * @param data
     * @return 
     * @create: 2016年1月5日 下午7:15:47 xieyj
     * @history:
     */
    public List<Navigation> queryNavigationList(Navigation data);

    /**
     * 导航修改
     * @param data
     * @return 
     * @create: 2016年1月5日 下午7:16:02 xieyj
     * @history:
     */
    public int refreshNavigation(Navigation data);

    /**
     * 导航状态修改
     * @param code
     * @param status
     * @param updater
     * @return 
     * @create: 2016年1月5日 下午7:17:06 xieyj
     * @history:
     */
    public int refreshNavigationStatus(String code, String status,
            String updater);

}
