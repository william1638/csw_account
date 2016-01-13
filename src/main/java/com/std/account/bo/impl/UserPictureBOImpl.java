/**
 * @Title UserPictureBOImpl.java 
 * @Package com.ibis.pz.user.impl 
 * @Description 
 * @author luoqi  
 * @date 2015年3月8日 上午11:25:38 
 * @version V1.0   
 */
package com.std.account.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.IUserPictureBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.dao.IUserPictureDAO;
import com.std.account.domain.UserPicture;
import com.std.account.enums.EBoolean;
import com.std.account.enums.EOrderStatus;

/** 
 * @author: luoqi 
 * @since: 2015年3月8日 上午11:25:38 
 * @history:
 */
@Component
public class UserPictureBOImpl extends PaginableBOImpl<UserPicture> implements
        IUserPictureBO {
    @Autowired
    private IUserPictureDAO userPictureDAO;

    /** 
     * @see com.ibis.pz.user.IUserPictureBO#queryUserPictureList(com.UserPicture.pz.domain.UserPictureDO)
     */
    @Override
    public List<UserPicture> queryUserPictureList(UserPicture data) {
        return userPictureDAO.selectList(data);
    }

    @Override
    public int saveUserPicture(String userId, String realName, String idKind,
            String idNo, String idPic1, String idPic2, String idUserPic) {

        int count = 0;
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(realName)
                && StringUtils.isNotBlank(idKind)
                && StringUtils.isNotBlank(idNo)
                && StringUtils.isNotBlank(idPic1)
                && StringUtils.isNotBlank(idPic2)
                && StringUtils.isNotBlank(idUserPic)) {
            UserPicture data = new UserPicture();
            data.setUserId(userId);
            data.setRealName(realName);
            data.setIdKind(idKind);
            data.setIdNo(idNo);

            data.setIdPic1(idPic1);
            data.setIdPic2(idPic2);
            data.setIdUserPic(idUserPic);
            data.setCreateDatetime(new Date());

            data.setVerifyStatus(EOrderStatus.UNAPPROVE.getCode());
            data.setRemark("待人工审批");
            count = userPictureDAO.insert(data);
        }
        return count;
    }

    @Override
    public int refreshVerifyUserPicture(Long id, String verifyUser,
            String verifyStatus, String remark) {
        int count = 0;
        if (id > 0 && StringUtils.isNotBlank(verifyUser)
                && StringUtils.isNotBlank(verifyStatus)) {
            UserPicture data = new UserPicture();
            data.setId(id);
            if (EBoolean.YES.getCode().equalsIgnoreCase(verifyStatus)) {
                data.setVerifyStatus(EOrderStatus.APPROVE_YES.getCode());
            } else {
                data.setVerifyStatus(EOrderStatus.APPROVE_NO.getCode());
            }
            data.setVerifyUser(verifyUser);
            data.setVerifyDatetime(new Date());
            data.setRemark(remark);
            count = userPictureDAO.updateVerifyUserPicture(data);
        }
        return count;
    }
}
