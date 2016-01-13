package com.std.account.dao;

import com.std.account.dao.base.IBaseDAO;
import com.std.account.domain.UserPicture;

/**
 * @author: luoqi 
 * @since: 2015年3月7日 下午6:28:30 
 * @history:
 */
public interface IUserPictureDAO extends IBaseDAO<UserPicture> {
    String NAMESPACE = IUserPictureDAO.class.getName().concat(".");

    int updateVerifyUserPicture(UserPicture data);
}
