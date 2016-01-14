package com.std.account.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.IUserExtBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.dao.IUserExtDAO;
import com.std.account.domain.UserExt;

/**
 * @author: luoqi 
 * @since: 2015年3月8日 上午11:12:12 
 * @history:
 */
@Component
public class UserExtBOImpl extends PaginableBOImpl<UserExt> implements
        IUserExtBO {
    @Autowired
    private IUserExtDAO userExtDAO;

    /**
     * @see com.ibis.pz.user.IUserExtBO#saveUserExt(com.UserExt.pz.domain.UserExtDO)
     */
    @Override
    public int saveUserExt(UserExt data) {
        int count = 0;
        if (data != null) {
            count = userExtDAO.insert(data);
        }
        return count;
    }

    /**
     * @see com.ibis.pz.user.IUserExtBO#removeUserExt(java.lang.String)
     */
    @Override
    public int removeUserExt(String userId) {
        int count = 0;
        if (StringUtils.isNotBlank(userId)) {
            UserExt data = new UserExt();
            data.setUserId(userId);
            count = userExtDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshUserExt(UserExt data) {
        int count = 0;
        if (data != null && StringUtils.isNotBlank(data.getUserId())) {
            return userExtDAO.update(data);
        }
        return count;

    }

    @Override
    public int refreshPhoto(String userId, String photo) {
        int count = 0;
        if (StringUtils.isNotBlank(userId)) {
            UserExt data = new UserExt();
            data.setUserId(userId);
            data.setPhoto(photo);
            return userExtDAO.updatePhoto(data);
        }
        return count;
    }

    /**
     * @see com.ibis.pz.user.IUserExtBO#getUserExt(java.lang.String)
     */
    @Override
    public UserExt getUserExt(String userId) {
        UserExt data = null;
        if (StringUtils.isNotBlank(userId)) {
            UserExt condition = new UserExt();
            condition.setUserId(userId);
            data = userExtDAO.select(condition);
        }
        return data;
    }

    /**
     * @see com.ibis.pz.user.IUserExtBO#queryUserExtList(com.UserExt.pz.domain.UserExtDO)
     */
    @Override
    public List<UserExt> queryUserExtList(UserExt data) {
        return userExtDAO.selectList(data);
    }

}
