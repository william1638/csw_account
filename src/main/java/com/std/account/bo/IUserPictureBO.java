/**
 * @Title IUserPictureBO.java 
 * @Package com.ibis.pz.user 
 * @Description 
 * @author luoqi  
 * @date 2015年3月8日 上午11:22:38 
 * @version V1.0   
 */
package com.std.account.bo;

import java.util.List;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.UserPicture;

/** 
 * @author: luoqi 
 * @since: 2015年3月8日 上午11:22:38 
 * @history:
 */
public interface IUserPictureBO extends IPaginableBO<UserPicture> {
    public int saveUserPicture(String userId, String realName, String idKind,
            String idNo, String idPic1, String idPic2, String idUserPic);

    public int refreshVerifyUserPicture(Long id, String verifyUser,
            String verifyResult, String remark);

    public List<UserPicture> queryUserPictureList(UserPicture data);

}
