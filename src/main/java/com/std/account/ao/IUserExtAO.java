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
import com.std.account.domain.UserExt;

/** 
 * @author: miyb 
 * @since: 2015-3-8 上午10:48:42 
 * @history:
 */
public interface IUserExtAO {
    String DEFAULT_ORDER_COLUMN = "user_id";

    /** 
     * 完善个人信息
     * @param userId
     * @param comefrom
     * @param birthday
     * @param qq
     * @param email
     * @param occupation
     * @param education 
     * @create: 2015-3-22 下午9:17:35 miyb
     * @history: 
     */
    public boolean doRichUserExtInfo(String userId, String comefrom,
            String birthday, String qq, String email, String occupation,
            String education);

    /** 
     * 设置头像
     * @param userId
     * @param photo
     * @return 
     * @create: 2015-5-17 下午1:04:22 miyb
     * @history: 
     */
    public boolean doRichPhoto(String userId, String photo);

    /** 
     * 获取个人扩展信息
     * @create: 2015-3-22 下午9:17:35 miyb
     * @history: 
     */
    public UserExt doGetUserExt(String userId);

    public Paginable<UserExt> queryUserExtPage(int start, int limit,
            UserExt condition);

}
