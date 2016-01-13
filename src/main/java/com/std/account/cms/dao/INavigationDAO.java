package com.std.account.cms.dao;

import com.std.account.cms.domain.Navigation;
import com.std.account.dao.base.IBaseDAO;

public interface INavigationDAO extends IBaseDAO<Navigation> {
    String NAMESPACE = INavigationDAO.class.getName().concat(".");

    public int update(Navigation data);

    public int updateStatus(Navigation data);
}
