package com.std.account.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.account.cms.dao.INavigationDAO;
import com.std.account.cms.domain.Navigation;
import com.std.account.dao.base.support.AMybatisTemplate;

@Repository("navigationDAOImpl")
public class NavigationDAOImpl extends AMybatisTemplate implements
        INavigationDAO {

    @Override
    public int insert(Navigation data) {
        return super.insert(NAMESPACE.concat("insert_navigation"), data);
    }

    @Override
    public int delete(Navigation data) {
        return super.delete(NAMESPACE.concat("delete_navigation"), data);
    }

    @Override
    public Navigation select(Navigation condition) {
        return super.select(NAMESPACE.concat("select_navigation"), condition,
            Navigation.class);
    }

    @Override
    public long selectTotalCount(Navigation condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_count_navigation"), condition);
    }

    @Override
    public List<Navigation> selectList(Navigation condition) {
        return super.selectList(NAMESPACE.concat("select_navigation"),
            condition, Navigation.class);
    }

    @Override
    public List<Navigation> selectList(Navigation condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_navigation"), start,
            count, condition, Navigation.class);
    }

    @Override
    public int update(Navigation data) {
        return super.update(NAMESPACE.concat("update_navigation"), data);
    }

    /** 
     * @see com.std.account.cms.dao.INavigationDAO#updateStatus(com.std.account.cms.domain.Navigation)
     */
    @Override
    public int updateStatus(Navigation data) {
        return super.update(NAMESPACE.concat("update_navigation_status"), data);
    }
}
