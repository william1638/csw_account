package com.std.account.cms.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.cms.bo.INavigationBO;
import com.std.account.cms.dao.INavigationDAO;
import com.std.account.cms.domain.Navigation;
import com.std.account.enums.ENavStatus;

@Component
public class NavigationBOImpl extends PaginableBOImpl<Navigation> implements
        INavigationBO {
    @Autowired
    INavigationDAO navigationDAO;

    @Override
    public boolean isNavigationExist(String code) {
        Navigation data = new Navigation();
        data.setCode(code);
        if (navigationDAO.selectTotalCount(data) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public int saveNavigation(Navigation data) {
        int count = 0;
        if (data != null) {
            data.setStatus(ENavStatus.UNUSE.getCode());
            data.setCreateDatetime(new Date());
            count = navigationDAO.insert(data);
        }
        return count;
    }

    @Override
    public int removeNavigation(String code) {
        if (StringUtils.isNotBlank(code)) {
            Navigation data = new Navigation();
            data.setCode(code);
            return navigationDAO.delete(data);
        }
        return 0;
    }

    @Override
    public int refreshNavigation(Navigation data) {
        data.setUpdateDatetime(new Date());
        return navigationDAO.update(data);
    }

    /** 
     * @see com.std.account.cms.bo.INavigationBO#refreshNavigationStatus(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public int refreshNavigationStatus(String code, String status,
            String updater) {
        Navigation data = new Navigation();
        data.setCode(code);
        data.setStatus(status);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        return navigationDAO.updateStatus(data);
    }

    @Override
    public Navigation getNavigation(String code) {
        if (StringUtils.isNotBlank(code)) {
            Navigation data = new Navigation();
            data.setCode(code);
            return navigationDAO.select(data);
        }
        return null;
    }

    @Override
    public List<Navigation> queryNavigationList(Navigation data) {
        return navigationDAO.selectList(data);
    }

}
