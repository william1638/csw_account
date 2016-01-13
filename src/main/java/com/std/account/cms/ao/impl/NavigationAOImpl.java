package com.std.account.cms.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.account.bo.base.Paginable;
import com.std.account.cms.ao.INavigationAO;
import com.std.account.cms.bo.INavigationBO;
import com.std.account.cms.domain.Navigation;
import com.std.account.enums.ENavStatus;
import com.std.account.exception.BizException;

@Service
public class NavigationAOImpl implements INavigationAO {
    @Autowired
    INavigationBO navigationBO;

    @Override
    public boolean addNavigation(Navigation data) {
        if (navigationBO.isNavigationExist(data.getCode())) {
            throw new BizException("XN700001", "导航编号已存在");
        }
        navigationBO.saveNavigation(data);
        return true;
    }

    @Override
    public boolean dropNavigation(String code) {
        if (!navigationBO.isNavigationExist(code)) {
            throw new BizException("XN700001", "导航编号不存在");
        }
        navigationBO.removeNavigation(code);
        return true;
    }

    @Override
    public boolean editNavigation(Navigation data) {
        if (!navigationBO.isNavigationExist(data.getCode())) {
            throw new BizException("XN700001", "导航编号不存在");
        }
        navigationBO.refreshNavigation(data);
        return true;
    }

    /** 
     * @see com.std.account.cms.ao.INavigationAO#changeNavigationStatus(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public boolean changeNavigationStatus(String code, String status,
            String updater) {
        if (!navigationBO.isNavigationExist(code)) {
            throw new BizException("XN700001", "导航编号不存在");
        }
        if (!ENavStatus.getNavStatusMap().containsKey(status)) {
            throw new BizException("XN700001", "导航状态不在枚举中");
        }
        Navigation data = new Navigation();
        data.setCode(code);
        data.setStatus(status);
        data.setUpdater(updater);
        navigationBO.refreshNavigationStatus(code, status, updater);
        return true;
    }

    @Override
    public List<Navigation> queryNavigationList(Navigation condition) {
        return navigationBO.queryNavigationList(condition);
    }

    @Override
    public Paginable<Navigation> queryNavigationPage(int start, int limit,
            Navigation condition) {
        return navigationBO.getPaginable(start, limit, condition);
    }
}
