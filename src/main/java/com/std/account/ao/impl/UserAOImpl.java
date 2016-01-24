/**
 * @Title UserAOImpl.java 
 * @Package com.ibis.pz.user.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-8 上午10:52:06 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.account.ao.IUserAO;
import com.std.account.bo.IAccountBO;
import com.std.account.bo.IBankCardBO;
import com.std.account.bo.ICompanyBO;
import com.std.account.bo.IIdentifyBO;
import com.std.account.bo.ISmsOutBO;
import com.std.account.bo.IUserBO;
import com.std.account.bo.IUserCompanyBO;
import com.std.account.bo.IUserExtBO;
import com.std.account.bo.IUserIdentifyBO;
import com.std.account.bo.IUserLockBO;
import com.std.account.bo.IUserLoginLogBO;
import com.std.account.bo.base.Paginable;
import com.std.account.common.DateUtil;
import com.std.account.common.MD5Util;
import com.std.account.common.PhoneUtil;
import com.std.account.common.PwdUtil;
import com.std.account.domain.Company;
import com.std.account.domain.User;
import com.std.account.domain.UserLock;
import com.std.account.domain.UserLoginLog;
import com.std.account.enums.EBankCardType;
import com.std.account.enums.EBoolean;
import com.std.account.enums.ECompanyStatus;
import com.std.account.enums.ECurrency;
import com.std.account.enums.EIDKind;
import com.std.account.enums.ELoginStatus;
import com.std.account.enums.ESmsBizType;
import com.std.account.enums.EUserKind;
import com.std.account.exception.BizException;
import com.std.account.util.RandomUtil;

/** 
 * @author: miyb 
 * @since: 2015-3-8 上午10:52:06 
 * @history:
 */
@Service
public class UserAOImpl implements IUserAO {
    @Autowired
    protected IUserBO userBO;

    @Autowired
    IIdentifyBO dentifyBO;

    @Autowired
    protected IAccountBO accountBO;

    @Autowired
    protected IUserExtBO userExtBO;

    @Autowired
    protected IBankCardBO bankCardBO;

    @Autowired
    protected IUserLoginLogBO userLoginLogBO;

    @Autowired
    protected IUserLockBO userLockBO;

    @Autowired
    IUserIdentifyBO userIdentifyBO;

    @Autowired
    ISmsOutBO smsOutBO;

    @Autowired
    ICompanyBO companyBO;

    @Autowired
    IUserCompanyBO userCompanyBO;

    /** 
     * @see com.ibis.account.ao.IUserAO#doGetUserByMobile(java.lang.String)
     */
    @Override
    public User doGetUserByMobile(String mobile) {
        return userBO.getUserByMobile(mobile);
    }

    @Override
    @Transactional
    public String doRegister(String mobile, String smsCaptcha, String loginPwd,
            String registerIp, String userReferee) {
        // 验证手机号
        userBO.isMobileExist(mobile);
        // 验证推荐人是否是平台的已注册用户
        userBO.checkUserReferee(userReferee);
        // 短信验证码是否正确
        smsOutBO.checkCaptcha(mobile, smsCaptcha,
            ESmsBizType.REGISTER.getCode());
        // 插入用户信息
        String userId = userBO.doRegister(mobile, loginPwd, registerIp,
            userReferee);
        // 记录注册日志
        userLoginLogBO.saveUserLoginLogBO(userId, registerIp,
            ELoginStatus.REGISTERSUCCESS.getCode());
        // 分配账号
        accountBO.distributeAccount(userId, ECurrency.CNY.getCode());
        // 发送短信
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + "用户，恭喜您成功注册。请妥善保管您的账户相关信息。", ESmsBizType.REGISTER.getCode(),
            ESmsBizType.REGISTER.getValue());
        return userId;
    }

    @Override
    public String doLogin(String loginName, String loginPwd, String loginType,
            String loginIp) {
        User condition = new User();
        condition.setMobile(loginName);
        condition.setLoginPwd(MD5Util.md5(loginPwd));
        List<User> userList = userBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList)) {
            // 记录错误日志
            if (EBoolean.YES.getCode().equalsIgnoreCase(loginType)) {
                userLoginLogBO.saveUserLoginLogBO(loginName, loginIp,
                    ELoginStatus.FAILURE.getCode());
            }
            throw new BizException("xn702002", "登录名或密码不正确");
        }
        // 记录正确日志
        User user = userList.get(0);
        if (EBoolean.YES.getCode().equalsIgnoreCase(loginType)) {
            userLoginLogBO.saveUserLoginLogBO(user.getUserId(), loginIp,
                ELoginStatus.SUCCESS.getCode());
        }
        return user.getUserId();
    }

    @Override
    public User doGetUser(String userId) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", userId + "用户不存在");
        }
        return user;
    }

    @Override
    @Transactional
    public void doSetTradePwd(String userId, String tradePwd, String smsCaptcha) {
        // 判断是否和登录密码重复
        User user = this.doGetUser(userId);
        // 短信验证码是否正确
        smsOutBO.checkCaptcha(user.getMobile(), smsCaptcha,
            ESmsBizType.SETTRADEPWD.getCode());
        userBO.refreshTradePwd(userId, tradePwd);
        // 发送短信
        String mobile = user.getMobile();
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + "用户，您的交易密码设置成功。请妥善保管您的账户相关信息。",
            ESmsBizType.SETTRADEPWD.getCode(),
            ESmsBizType.SETTRADEPWD.getValue());
    }

    @Override
    @Transactional
    public boolean doFindLoginPwd(String mobile, String newLoginPwd,
            String smsCaptcha) {
        User user = userBO.getUserByMobile(mobile);
        if (user == null) {// 这里其实还有一种处理方法：就是直接注册
            throw new BizException("li01004", "用户不存在,请先注册");
        }
        // 短信验证码是否正确
        smsOutBO.checkCaptcha(mobile, smsCaptcha,
            ESmsBizType.FINDLOGINPWD.getCode());

        userBO.refreshLoginPwd(user.getUserId(), MD5Util.md5(newLoginPwd),
            PwdUtil.calculateSecurityLevel(newLoginPwd));
        // 发送短信
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + "用户，您的登录密码找回成功。请妥善保管您的账户相关信息。",
            ESmsBizType.FINDLOGINPWD.getCode(),
            ESmsBizType.FINDLOGINPWD.getValue());
        return true;
    }

    /** 
     * @see com.ibis.pz.user.IUserAO#doResetLoginPwd(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @Transactional
    public boolean doResetLoginPwd(String userId, String oldLoginPwd,
            String newLoginPwd) {
        if (oldLoginPwd.equals(newLoginPwd)) {
            throw new BizException("li01006", "新登录密码不能与原有密码重复");
        }
        // 验证当前登录密码是否正确
        userBO.checkLoginPwd(userId, oldLoginPwd);
        // 重置
        userBO.refreshLoginPwd(userId, MD5Util.md5(newLoginPwd),
            PwdUtil.calculateSecurityLevel(newLoginPwd));

        // 发送短信
        User user = userBO.getUser(userId);
        String mobile = user.getMobile();
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + "用户，您的登录密码修改成功。请妥善保管您的账户相关信息。",
            ESmsBizType.RESETLOGINPWD.getCode(),
            ESmsBizType.RESETLOGINPWD.getValue());
        return true;
    }

    @Override
    @Transactional
    public boolean doFindTradePwd(String userId, String newTradePwd,
            String smsCaptcha, String idKind, String idNo) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", "用户名不存在");
        }
        if (user.getIdKind() == null || user.getIdNo() == null) {
            throw new BizException("li01004", "请先实名认证");
        }
        // 证件是否正确
        if (!(user.getIdKind().equalsIgnoreCase(idKind) && user.getIdNo()
            .equalsIgnoreCase(idNo))) {
            throw new BizException("li01009", "证件验证不通过");
        }

        // 短信验证码是否正确
        String mobile = user.getMobile();
        smsOutBO.checkCaptcha(mobile, smsCaptcha,
            ESmsBizType.FINDTRADEPWD.getCode());
        userBO.refreshTradePwd(userId, newTradePwd);
        // 发送短信
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + "用户，您的交易密码找回成功。请妥善保管您的账户相关信息。",
            ESmsBizType.FINDTRADEPWD.getCode(),
            ESmsBizType.FINDTRADEPWD.getValue());
        return true;
    }

    /** 
     * @see com.ibis.pz.user.IUserAO#doResetTradePwd(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @Transactional
    public boolean doResetTradePwd(String userId, String oldTradePwd,
            String newTradePwd) {
        if (oldTradePwd.equals(newTradePwd)) {
            throw new BizException("li01008", "新安全密码与原有安全密码重复");
        }
        User user = null;
        User conditon = new User();
        conditon.setUserId(userId);
        conditon.setTradePwd(MD5Util.md5(oldTradePwd));
        List<User> list = userBO.queryUserList(conditon);
        if (CollectionUtils.isNotEmpty(list)) {
            user = list.get(0);
        } else {
            throw new BizException("li01008", "旧安全密码不正确");
        }
        userBO.refreshTradePwd(userId, newTradePwd);
        // 发送短信
        String mobile = user.getMobile();
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + "用户，您的交易密码修改成功。请妥善保管您的账户相关信息。",
            ESmsBizType.RESETTRADEPWD.getCode(),
            ESmsBizType.RESETTRADEPWD.getValue());
        return true;
    }

    @Override
    @Transactional
    public boolean doChangeMoblie(String userId, String newMobile,
            String smsCaptcha, String tradePwd) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", "用户名不存在");
        }
        String oldMobile = user.getMobile();
        if (newMobile.equals(oldMobile)) {
            throw new BizException("li01009", "新手机与原手机一致");
        }
        userBO.isMobileExist(newMobile);
        // 验证交易密码
        userBO.checkTradePwd(userId, tradePwd);
        // 短信验证码是否正确（往新手机号发送）
        smsOutBO.checkCaptcha(newMobile, smsCaptcha,
            ESmsBizType.CHANGEMOBILE.getCode());
        userBO.refreshMobile(userId, newMobile);
        // 发送短信
        smsOutBO.sendSmsOut(
            oldMobile,
            "尊敬的"
                    + PhoneUtil.hideMobile(oldMobile)
                    + "用户，您于"
                    + DateUtil.dateToStr(new Date(),
                        DateUtil.DATA_TIME_PATTERN_1)
                    + "提交的更改绑定手机号码服务审核通过，您的新绑定手机号码为" + newMobile
                    + "，请妥善保管您的账户相关信息。", ESmsBizType.CHANGEMOBILE.getCode(),
            ESmsBizType.CHANGEMOBILE.getValue());
        return true;
    }

    /** 
     * @see com.ibis.account.ao.IUserAO#queryUserPage(int, int, com.User.account.domain.UserDO)
     */
    @Override
    public Paginable<User> queryUserPage(int start, int limit, User condition) {
        return userBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<User> queryUserList(User condition) {
        return userBO.queryUserList(condition);
    }

    @Override
    public void doCheckMobile(String mobile) {
        User condition = new User();
        condition.setMobile(mobile);
        List<User> userList = queryUserList(condition);
        if (CollectionUtils.isNotEmpty(userList)) {
            throw new BizException("li01009", "手机号已存在");
        }
    }

    @Override
    public UserLoginLog doGetLatestUserLoginLog(String userId) {
        return userLoginLogBO.getLatestUserLoginLog(userId);
    }

    @Override
    public Paginable<UserLoginLog> queryUserLoginLogPage(int start, int limit,
            UserLoginLog condition) {
        return userLoginLogBO.getPaginable(start, limit, condition);
    }

    @Override
    public Paginable<UserLock> queryUserLockPage(int start, int limit,
            UserLock condition) {
        return userLockBO.getPaginable(start, limit, condition);
    }

    @Override
    public void checkTradePwd(String userId, String tradePwd) {
        userBO.checkTradePwd(userId, tradePwd);
    }

    @Override
    @Transactional
    public String doAddUser(String mobile, String idKind, String idNo,
            String realName, String bankCode, String bankName,
            String bankCardNo, String subbranch, String bindMobile,
            String userReferee) {
        // 验证手机号
        userBO.isMobileExist(mobile);
        // 插入用户信息
        String loginPsd = RandomUtil.generate6();
        String tradePsd = RandomUtil.generate6();
        String userId = userBO.doAddUser(mobile, loginPsd, userReferee,
            realName, idKind, idNo, tradePsd);
        // 记录注册日志
        userLoginLogBO.saveUserLoginLogBO(userId, "255.255.255.0",
            ELoginStatus.REGISTERSUCCESS.getCode());
        // 三方认证
        dentifyBO.doIdentify(userId, realName, idKind, idNo);
        // 保存用户认证记录
        userIdentifyBO.saveUserIdentify(userId, realName,
            EIDKind.IDCard.getCode(), idNo, "0", "success");
        // 绑定银行卡信息
        bankCardBO.saveBankCard(userId, EBankCardType.User.getCode(), bankCode,
            bankName, bankCardNo, subbranch, bindMobile);
        // 分配账号
        accountBO.distributeAccount(userId, ECurrency.CNY.getCode());
        // // 发送短信
        // smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
        // + "用户，您已成功注册。您的登录密码为" + loginPsd + ";交易密码为" + tradePsd
        // + "，请及时登录个金所网站修改密码。如有疑问，请联系客服：400-0008-139。",
        // ESmsBizType.FAREN_ADD.getCode(), ESmsBizType.FAREN_ADD.getValue());
        return userId;
    }

    @Override
    public void doKYC(String companyId, String kycUser, String kycResult,
            String kycNote, String serveList, String quoteList, Integer level) {
        Company company = companyBO.getCompany(companyId);
        if (!ECompanyStatus.todoKYC.getCode().equalsIgnoreCase(
            company.getStatus())) {
            throw new BizException("xn000001", "当前公司不处于待KYC阶段");
        }
        User admin = null;
        List<User> userList = userCompanyBO.queryUserList(companyId);
        if (CollectionUtils.isNotEmpty(userList)) {
            for (User user : userList) {
                if (EUserKind.Admin.getCode().equalsIgnoreCase(
                    user.getUserKind())) {
                    admin = user;
                    break;
                }
            }
        }
        if (admin == null) {
            throw new BizException("xn000001", "当前公司无对应admin,不能KYC");
        }
        companyBO.doKYC(companyId, kycUser, kycResult, kycNote);
        if (EBoolean.YES.getCode().equalsIgnoreCase(kycResult)) {
            userBO.doKYC(admin.getUserId(), serveList, quoteList, level);
        }

    }

}
