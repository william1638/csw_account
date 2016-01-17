/**
 * @Title IUserAO.java 
 * @Package com.ibis.pz.user 
 * @Description 
 * @author miyb  
 * @date 2015-3-8 上午10:48:42 
 * @version V1.0   
 */
package com.std.account.ao;

import java.util.List;

import com.std.account.bo.base.Paginable;
import com.std.account.domain.User;
import com.std.account.domain.UserLock;
import com.std.account.domain.UserLoginLog;

/** 
 * @author: miyb 
 * @since: 2015-3-8 上午10:48:42 
 * @history:
 */
public interface IUserAO {
    String DEFAULT_ORDER_COLUMN = "user_id";

    /** 
     * 根据手机号获取用户信息
     * @param mobile
     * @return 
     * @create: 2015-5-24 下午4:37:01 miyb
     * @history: 
     */
    public User doGetUserByMobile(String mobile);

    /**
     * 注册
     * @param mobile
     * @param smsCaptcha
     * @param loginPwd
     * @param registerIp
     * @param userReferee
     * @return 
     * @create: 2016年1月13日 下午8:55:32 myb858
     * @history:
     */
    public String doRegister(String mobile, String smsCaptcha, String loginPwd,
            String registerIp, String userReferee);

    /**
     * 用户登陆
     * @param loginName
     * @param loginPwd
     * @param loginType
     * @param loginIp
     * @return 
     * @create: 2015年10月15日 下午2:45:52 myb858
     * @history:
     */

    public String doLogin(String loginName, String loginPwd, String loginType,
            String loginIp);

    /**
     * 查询用户的详细信息
     * @param userId
     * @create: 2014-12-10 下午7:37:18 miyb
     * @history:
     */
    public User doGetUser(String userId);

    /**
     * 设置交易密码
     * @param userId
     * @param tradePwd
     * @param smsCaptcha
     * @return 
     * @create: 2015年9月19日 上午11:03:29 myb858
     * @history:
     */
    public void doSetTradePwd(String userId, String tradePwd, String smsCaptcha);

    /**
     * 找回登录密码
     * @param mobile
     * @param newLoginPwd
     * @param smsCaptcha
     * @return 
     * @create: 2015年9月15日 下午8:51:01 myb858
     * @history:
     */
    public boolean doFindLoginPwd(String mobile, String newLoginPwd,
            String smsCaptcha);

    /** 
     * 重置登录密码
     * @param userId
     * @param oldLoginPwd
     * @param newLoginPwd
     * @return 
     * @create: 2015-5-17 上午10:35:14 miyb
     * @history: 
     */
    public boolean doResetLoginPwd(String userId, String oldLoginPwd,
            String newLoginPwd);

    /**
     * 找回交易密码
     * @param userId
     * @param newTradePwd
     * @param smsCaptcha
     * @param idKind
     * @param idNo
     * @return 
     * @create: 2015年9月16日 下午3:38:33 myb858
     * @history:
     */
    public boolean doFindTradePwd(String userId, String newTradePwd,
            String smsCaptcha, String idKind, String idNo);

    /** 
     * 重置交易密码
     * @param userId
     * @param oldTradePwd
     * @param newTradePwd
     * @return 
     * @create: 2015-5-17 上午11:00:13 miyb
     * @history: 
     */
    public boolean doResetTradePwd(String userId, String oldTradePwd,
            String newTradePwd);

    /**
     * 更换手机号
     * @param userId
     * @param newMobile
     * @param smsCaptcha
     * @param tradePwd
     * @return 
     * @create: 2015年9月17日 下午12:42:33 myb858
     * @history:
     */
    public boolean doChangeMoblie(String userId, String newMobile,
            String smsCaptcha, String tradePwd);

    /** 
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2015-6-7 上午10:04:12 miyb
     * @history: 
     */
    public Paginable<User> queryUserPage(int start, int limit, User condition);

    /**
     * 
     * @param condition
     * @return 
     * @create: 2015年10月28日 下午2:41:48 myb858
     * @history:
     */
    public List<User> queryUserList(User condition);

    /**
     * 获取最新登录日志
     * @param userId
     * @return 
     * @create: 2015年9月29日 上午10:51:10 myb858
     * @history:
     */
    public UserLoginLog doGetLatestUserLoginLog(String userId);

    /**
     * 
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2015年10月28日 下午2:54:23 myb858
     * @history:
     */
    public Paginable<UserLoginLog> queryUserLoginLogPage(int start, int limit,
            UserLoginLog condition);

    /**
     * 
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2015年10月28日 下午3:00:43 myb858
     * @history:
     */
    public Paginable<UserLock> queryUserLockPage(int start, int limit,
            UserLock condition);

    /**
     * 校验交易密码
     * @param userId
     * @param tradePwd
     * @return 
     * @create: 2015年11月10日 上午9:16:42 myb858
     * @history:
     */
    public boolean checkTradePwd(String userId, String tradePwd);

    /**
     * 代注册
     * @param mobile
     * @param userReferee
     * @param realName
     * @param idKind
     * @param idNo
     * @param bankCode
     * @param bankName
     * @param bankCardNo
     * @param subbranch
     * @param bindMobile
     * @return 
     * @create: 2015年12月6日 下午2:16:54 myb858
     * @history:
     */
    public String doAddUser(String mobile, String idKind, String idNo,
            String realName, String bankCode, String bankName,
            String bankCardNo, String subbranch, String bindMobile,
            String userReferee);

    /**
     * KYC
     * @param companyId
     * @param kycUser
     * @param kycResult
     * @param kycNote
     * @param serveList
     * @param quoteList
     * @param level 
     * @create: 2016年1月16日 下午1:46:54 myb858
     * @history:
     */
    public void doKYC(String companyId, String kycUser, String kycResult,
            String kycNote, String serveList, String quoteList, String level);

}
