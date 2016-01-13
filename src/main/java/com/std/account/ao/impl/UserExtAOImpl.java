package com.std.account.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.account.ao.IUserExtAO;
import com.std.account.bo.IUserExtBO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.UserExt;

/**
 * @author: luoqi 
 * @since: 2015年3月8日 上午11:12:12 
 * @history:
 */
@Service
public class UserExtAOImpl implements IUserExtAO {
    @Autowired
    protected IUserExtBO userExtBO;

    /** 
     * @see com.ibis.pz.user.IUserExtAO#doRichUserExtInfo(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @Transactional
    public boolean doRichUserExtInfo(String userId, String comefrom,
            String birthday, String qq, String email, String occupation,
            String education) {
        UserExt data = new UserExt();
        data.setUserId(userId);
        data.setComefrom(comefrom);
        data.setBirthday(birthday);
        data.setQq(qq);
        data.setEmail(email);
        data.setOccupation(occupation);
        data.setEducation(education);
        UserExt resultDO = userExtBO.getUserExt(userId);
        if (resultDO == null) {
            userExtBO.saveUserExt(data);
        } else {
            userExtBO.refreshUserExt(data);
        }
        return true;
    }

    /** 
     * @see com.ibis.pz.user.IUserExtAO#doRichPhoto(java.lang.String, java.lang.String)
     */
    @Override
    @Transactional
    public boolean doRichPhoto(String userId, String photo) {
        // 更新用户扩展信息
        UserExt resultDO = userExtBO.getUserExt(userId);
        if (resultDO == null) {
            UserExt userExtDO = new UserExt();
            userExtDO.setUserId(userId);
            userExtDO.setPhoto(photo);
            userExtBO.saveUserExt(userExtDO);
        } else {
            userExtBO.refreshPhoto(userId, photo);
        }
        return true;
    }

    @Override
    public UserExt doGetUserExt(String userId) {
        return userExtBO.getUserExt(userId);
    }

    @Override
    public Paginable<UserExt> queryUserExtPage(int start, int limit,
            UserExt condition) {
        return userExtBO.getPaginable(start, limit, condition);
    }
}
