/**
 * @Title UserIdentifyAOImpl.java 
 * @Package com.ibis.pz.user.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-8 上午10:52:06 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.account.ao.IIdentityAO;
import com.std.account.bo.IIdentifyBO;
import com.std.account.bo.IUserBO;
import com.std.account.bo.IUserIdentifyBO;
import com.std.account.bo.IUserPictureBO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.UserIdentify;
import com.std.account.domain.UserPicture;
import com.std.account.enums.EIDKind;

/** 
 * @author: miyb 
 * @since: 2015-3-8 上午10:52:06 
 * @history:
 */
@Service
public class IdentityAOImpl implements IIdentityAO {
    @Autowired
    IUserIdentifyBO userIdentifyBO;

    @Autowired
    IIdentifyBO dentifyBO;

    @Autowired
    protected IUserBO userBO;

    @Autowired
    protected IUserPictureBO userPictureBO;

    @Override
    @Transactional
    public void doIdentify(String userId, String idKind, String idNo,
            String realName) {
        userIdentifyBO.isChecked(realName, idKind, idNo);
        // 三方认证
        dentifyBO.doIdentify(userId, realName, idKind, idNo);
        // 更新用户表
        userBO
            .refreshIdentity(userId, realName, EIDKind.IDCard.getCode(), idNo);
        // 保存用户认证记录
        userIdentifyBO.saveUserIdentify(userId, realName,
            EIDKind.IDCard.getCode(), idNo, "0", "success");
    }

    @Override
    public Paginable<UserIdentify> queryUserIdentifyPage(int start, int limit,
            UserIdentify condition) {
        return userIdentifyBO.getPaginable(start, limit, condition);
    }

    @Override
    public Paginable<UserPicture> queryUserPicturePage(int start, int limit,
            UserPicture condition) {
        return userPictureBO.getPaginable(start, limit, condition);
    }

    @Override
    @Transactional
    public boolean doSaveUserPicture(String userId, String realName,
            String idKind, String idNo, String idPic1, String idPic2,
            String idUserPic) {
        userPictureBO.saveUserPicture(userId, realName, idKind, idNo, idPic1,
            idPic2, idUserPic);
        return true;
    }

    @Override
    @Transactional
    public boolean doVerifyUserPicture(Long id, String verifyUser,
            String verifyStatus, String remark) {
        userPictureBO.refreshVerifyUserPicture(id, verifyUser, verifyStatus,
            remark);
        return true;
    }
}
