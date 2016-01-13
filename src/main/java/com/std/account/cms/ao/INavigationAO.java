package com.std.account.cms.ao;

import java.util.List;

import com.std.account.bo.base.Paginable;
import com.std.account.cms.domain.Navigation;

public interface INavigationAO {
    String DEFAULT_ORDER_COLUMN = "order_no";

    /**
     * 增加
     * @param Navigation
     * @return 
     * @create: 2015年8月25日 下午3:18:51 myb858
     * @history:
     */
    public boolean addNavigation(Navigation data);

    /**
     * 删除
     * @param code
     * @return 
     * @create: 2015年8月25日 下午3:19:28 myb858
     * @history:
     */
    public boolean dropNavigation(String code);

    /**
     * 修改
     * @param data
     * @return 
     * @create: 2015年8月25日 下午3:21:17 myb858
     * @history:
     */
    public boolean editNavigation(Navigation data);

    /**
     * 导航状态修改
     * @param code
     * @param status
     * @param updater
     * @return 
     * @create: 2016年1月5日 下午7:20:28 xieyj
     * @history:
     */
    public boolean changeNavigationStatus(String code, String status,
            String updater);

    /**
     * 列表查询
     * @param condition
     * @return 
     * @create: 2015年8月25日 下午3:20:59 myb858
     * @history:
     */
    public List<Navigation> queryNavigationList(Navigation condition);

    /** 
     * 分页查询
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2015年8月25日 下午3:20:28 myb858
     * @history:
     */
    public Paginable<Navigation> queryNavigationPage(int start, int limit,
            Navigation condition);

}
