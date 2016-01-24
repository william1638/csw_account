/**
 * @Title UserBOImpl.java 
 * @Package com.ibis.pz.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-7 上午9:21:25 
 * @version V1.0   
 */
package com.std.account.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.IUserBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.common.MD5Util;
import com.std.account.common.PhoneUtil;
import com.std.account.common.PwdUtil;
import com.std.account.core.OrderNoGenerater;
import com.std.account.dao.IUserDAO;
import com.std.account.dao.IUserLoginLogDAO;
import com.std.account.domain.User;
import com.std.account.enums.EAccountStatus;
import com.std.account.enums.EUserKind;
import com.std.account.exception.BizException;

/** 
 * @author: miyb 
 * @since: 2015-3-7 上午9:21:25 
 * @history:
 */
@Component
public class UserBOImpl extends PaginableBOImpl<User> implements IUserBO {
    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private IUserLoginLogDAO userLoginLogDAO;

    /** 
     * @see com.ibis.pz.user.IUserBO#removeUser(java.lang.String)
     */
    @Override
    public int removeUser(String userId) {
        int count = 0;
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            count = userDAO.delete(data);
        }
        return count;
    }

    /**
     * @see com.ibis.pz.user.IUserBO#refreshIdentity(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public int refreshIdentity(String userId, String realName, String idKind,
            String idNo) {
        User data = new User();
        data.setUserId(userId);
        data.setRealName(realName);
        data.setIdKind(idKind);
        data.setIdNo(idNo);
        int count = 0;
        if (data != null && StringUtils.isNotBlank(data.getUserId())) {
            count = userDAO.updateIdentity(data);
        }
        return count;
    }

    /**
     * @see com.ibis.pz.user.IUserBO#refreshTradePwd(java.lang.String, java.lang.String)
     */
    @Override
    public int refreshTradePwd(String userId, String tradePwd) {
        int count = 0;
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setTradePwd(MD5Util.md5(tradePwd));
            data.setTradePwdStrength(PwdUtil.calculateSecurityLevel(tradePwd));
            count = userDAO.updateTradePwd(data);
        }
        return count;
    }

    /** 
     * @see com.ibis.pz.user.IUserBO#getUser(java.lang.String)
     */
    @Override
    public User getUser(String userId) {
        User data = null;
        if (StringUtils.isNotBlank(userId)) {
            User condition = new User();
            condition.setUserId(userId);
            data = userDAO.select(condition);
        }
        return data;
    }

    /** 
     * @see com.ibis.pz.user.IUserBO#getUserByMobile(java.lang.String)
     */
    @Override
    public User getUserByMobile(String mobile) {
        User data = null;
        if (StringUtils.isNotBlank(mobile)) {
            User condition = new User();
            condition.setMobile(mobile);
            List<User> list = userDAO.selectList(condition);
            if (list != null && list.size() > 1) {
                throw new BizException("li01006", "手机号重复");
            }
            if (CollectionUtils.isNotEmpty(list)) {
                data = list.get(0);
            }
        }
        return data;
    }

    /** 
     * @see com.ibis.pz.user.IUserBO#queryUserList(com.User.pz.domain.UserDO)
     */
    @Override
    public List<User> queryUserList(User data) {
        return userDAO.selectList(data);
    }

    /**
     * 
     * @see com.ibis.pz.user.IUserBO#refreshLoginPwd(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public int refreshLoginPwd(String userId, String loginPwd,
            String loginPwdStrength) {
        int count = 0;
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setLoginPwd(loginPwd);
            data.setLoginPwdStrength(loginPwdStrength);
            count = userDAO.updateLoginPwd(data);
        }
        return count;
    }

    /**
     * @see com.ibis.pz.user.IUserBO#refreshMobile(java.lang.String, java.lang.String)
     */
    @Override
    public int refreshMobile(String userId, String mobile) {
        // 手机号校验
        isMobileExist(mobile);
        User data = new User();
        data.setUserId(userId);
        data.setMobile(mobile);
        int count = 0;
        if (data != null && StringUtils.isNotBlank(data.getUserId())) {
            count = userDAO.updateMobile(data);
        }
        return count;
    }

    /** 
     * @see com.ibis.pz.user.IUserBO#isMobileExist(java.lang.String)
     */
    @Override
    public void isMobileExist(String mobile) {
        // 判断格式
        PhoneUtil.checkMobile(mobile);
        User condition = new User();
        condition.setMobile(mobile);
        long count = getTotalCount(condition);
        if (count > 0) {
            throw new BizException("li01003", "手机号已经存在");
        }
    }

    /** 
     * @see com.std.account.bo.IUserBO#checkUserReferee(java.lang.String)
     */
    @Override
    public void checkUserReferee(String userReferee) {
        if (StringUtils.isNotBlank(userReferee)) {
            // 判断格式
            PhoneUtil.checkMobile(userReferee, "推荐人");
            User condition = new User();
            condition.setMobile(userReferee);
            long count = getTotalCount(condition);
            if (count <= 0) {
                throw new BizException("li01003", "推荐人不存在");
            }
        }
    }

    /** 
     * @see com.ibis.pz.user.IUserBO#doRegister(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String doRegister(String mobile, String loginPwd, String registerIp,
            String userReferee) {
        String userId = null;
        if (StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(loginPwd)
                && StringUtils.isNotBlank(registerIp)) {
            Date now = new Date();
            User user = new User();
            userId = OrderNoGenerater.generate("U");
            user.setUserId(userId);
            user.setMobile(mobile);
            user.setLoginPwd(MD5Util.md5(loginPwd));
            user.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
            user.setUserKind(EUserKind.Admin.getCode());
            user.setUserReferee(userReferee);
            user.setCreateDatetime(now);
            user.setStatus(EAccountStatus.NORMAL.getCode());// 0正常;1程序锁定;2人工锁定
            userDAO.insert(user);
        }
        return userId;
    }

    @Override
    public void checkTradePwd(String userId, String tradePwd) {
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(tradePwd)) {
            User condition = new User();
            condition.setUserId(userId);
            condition.setTradePwd(MD5Util.md5(tradePwd));
            long count = this.getTotalCount(condition);
            if (count != 1) {
                throw new BizException("jd00001", "交易密码错误");
            }
        } else {
            throw new BizException("jd00001", "交易密码错误");
        }
    }

    @Override
    public void checkLoginPwd(String userId, String loginPwd) {
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(loginPwd)) {
            User condition = new User();
            condition.setUserId(userId);
            condition.setLoginPwd(MD5Util.md5(loginPwd));
            long count = this.getTotalCount(condition);
            if (count != 1) {
                throw new BizException("jd00001", "原登录密码错误");
            }
        } else {
            throw new BizException("jd00001", "原登录密码错误");
        }

    }

    @Override
    public String doAddUser(String mobile, String loginPsd, String userReferee,
            String realName, String idKind, String idNo, String tradePsd) {
        String userId = null;
        if (StringUtils.isNotBlank(mobile)) {
            User user = new User();
            userId = OrderNoGenerater.generate("U");
            user.setUserId(userId);
            user.setMobile(mobile);
            user.setLoginPwd(MD5Util.md5(loginPsd));
            user.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPsd));
            user.setUserKind(EUserKind.Admin.getCode());
            user.setUserReferee(userReferee);
            user.setIdKind(idKind);
            user.setIdNo(idNo);
            user.setRealName(realName);
            user.setTradePwd(MD5Util.md5(tradePsd));
            user.setTradePwdStrength(PwdUtil.calculateSecurityLevel(tradePsd));
            user.setCreateDatetime(new Date());
            user.setRemark(EUserKind.Admin.getValue());
            user.setStatus(EAccountStatus.NORMAL.getCode());
            userDAO.insertFaRen(user);
        }
        return userId;
    }

    @Override
    public int doKYC(String userId, String serveList, String quoteList,
            Integer level) {
        int count = 0;
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setServeList(serveList);
            data.setQuoteList(quoteList);
            data.setLevel(level);
            count = userDAO.doKYC(data);
        }
        return count;
    }
}
