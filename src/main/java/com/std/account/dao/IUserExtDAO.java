package com.std.account.dao;

import com.std.account.dao.base.IBaseDAO;
import com.std.account.domain.UserExt;

/**
 * @author: luoqi 
 * @since: 2015年3月7日 下午6:25:28 
 * @history:
 */
public interface IUserExtDAO extends IBaseDAO<UserExt> {
    String NAMESPACE = IUserExtDAO.class.getName().concat(".");

    /**
     * 更新基本信息
     * @param data
     * @return 
     * @create: 2015-3-14 下午4:43:25 xieyj
     * @history:
     */
    public int update(UserExt data);

    /**
     * 更新头像
     * @param data
     * @return 
     * @create: 2015-3-14 下午4:43:25 xieyj
     * @history:
     */
    public int updatePhoto(UserExt data);

}
