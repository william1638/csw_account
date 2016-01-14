/**
 * @Title IUserIdentifyAO.java 
 * @Package com.ibis.pz.user 
 * @Description 
 * @author miyb  
 * @date 2015-3-8 上午10:48:42 
 * @version V1.0   
 */
package com.std.account.ao;

import com.std.account.bo.base.Paginable;
import com.std.account.domain.UserIdentify;
import com.std.account.domain.UserPicture;

/** 
 * @author: miyb 
 * @since: 2015-3-8 上午10:48:42 
 * @history:
 */
public interface IIdentityAO {
    String DEFAULT_ORDER_COLUMN = "user_id";

    /**
     * 三方实名认证
     * @param userId
     * @param idKind
     * @param idNo
     * @param realName
     * @return 
     * @create: 2016年1月13日 下午9:00:53 myb858
     * @history:
     */
    public void doIdentify(String userId, String idKind, String idNo,
            String realName);

    /**
     * 
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2015年10月28日 下午3:08:37 myb858
     * @history:
     */
    public Paginable<UserIdentify> queryUserIdentifyPage(int start, int limit,
            UserIdentify condition);

    /**
     * 
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2015年10月28日 下午3:18:33 myb858
     * @history:
     */
    public Paginable<UserPicture> queryUserPicturePage(int start, int limit,
            UserPicture condition);

    /**
     * 插入用户照片待人工审核
     * @param userId
     * @param realName
     * @param idKind
     * @param idNo
     * @param idPic1
     * @param idPic2
     * @param idUserPic
     * @return 
     * @create: 2015年10月28日 下午4:00:03 myb858
     * @history:
     */
    public boolean doSaveUserPicture(String userId, String realName,
            String idKind, String idNo, String idPic1, String idPic2,
            String idUserPic);

    /**
     * 人工看身份证照片审批实名认证
     * @param id
     * @param verifyUser
     * @param verifyStatus
     * @param remark
     * @return 
     * @create: 2015年10月28日 下午3:28:00 myb858
     * @history:
     */
    public boolean doVerifyUserPicture(Long id, String verifyUser,
            String verifyStatus, String remark);

}
