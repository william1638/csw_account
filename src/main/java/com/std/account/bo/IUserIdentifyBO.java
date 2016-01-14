/**
 * @Title IUserIdentifyBO.java 
 * @Package com.ibis.pz.user 
 * @Description 
 * @author luoqi  
 * @date 2015年3月8日 上午11:30:08 
 * @version V1.0   
 */
package com.std.account.bo;

import java.util.List;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.UserIdentify;

/** 
 * @author: luoqi 
 * @since: 2015年3月8日 上午11:30:08 
 * @history:
 */
public interface IUserIdentifyBO extends IPaginableBO<UserIdentify> {
    /**
     * 一个身份证只能被认证一次
     * @param realName
     * @param idKind
     * @param idNo 
     * @create: 2015年12月6日 下午2:53:02 myb858
     * @history:
     */
    public void isChecked(String realName, String idKind, String idNo);

    public int saveUserIdentify(String userId, String realName, String idKind,
            String idcard, String errorCode, String errorInfo);

    public List<UserIdentify> queryUserIdentifyList(String userId);

    public List<UserIdentify> queryTodayIdentifyList(String userId);

    public UserIdentify getSuccessOne(String realName, String idKind,
            String idNo);

    public List<UserIdentify> queryUserIdentifyList(UserIdentify data);

}
