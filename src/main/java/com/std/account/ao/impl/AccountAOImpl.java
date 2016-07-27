/**
 * @Title AccountAOImpl.java 
 * @Package com.ibis.account.ao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-17 下午6:21:25 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.account.ao.IAccountAO;
import com.std.account.bo.IAJourBO;
import com.std.account.bo.IAccountBO;
import com.std.account.bo.IHLOrderBO;
import com.std.account.bo.IUserBO;
import com.std.account.bo.IWithdrawBO;
import com.std.account.bo.IZZOrderBO;
import com.std.account.bo.base.Paginable;
import com.std.account.common.PhoneUtil;
import com.std.account.common.PropertiesUtil;
import com.std.account.domain.Account;
import com.std.account.dto.res.XN805901Res;
import com.std.account.enums.EBizType;
import com.std.account.enums.ECurrency;
import com.std.account.enums.EUserKind;
import com.std.account.exception.BizException;

/** 
 * @author: miyb 
 * @since: 2015-3-17 下午6:21:25 
 * @history:
 */
@Service
public class AccountAOImpl implements IAccountAO {
    @Autowired
    IAccountBO accountBO;

    @Autowired
    IAJourBO aJourBO;

    @Autowired
    IWithdrawBO withdrawBO;

    @Autowired
    IHLOrderBO hlOrderBO;

    @Autowired
    IZZOrderBO zzOrderBO;

    @Autowired
    IUserBO userBO;

    @Override
    public String distributeAccount(String userId, String realName,
            ECurrency currency) {
        return accountBO.distributeAccount(userId, realName, currency);
    }

    @Override
    @Transactional
    public String distributeAccountTwo(String userId, String realName,
            ECurrency currency, String userReferee) {
        XN805901Res f3UserRes = userBO.getRemoteUser(userReferee, userReferee);
        if (f3UserRes == null) {
            throw new BizException("li779001", "积分商用户编号不存在");
        }
        Long amount = Long.valueOf(PropertiesUtil.Config.FIRST_INTEGRAL);
        Account f3Account = accountBO.getAccountByUserId(userReferee);
        String accountNumber = accountBO.distributeAccount(userId, realName,
            currency, amount);
        aJourBO.addJour(accountNumber, 0L, amount, EBizType.AJ_RZJ.getCode(),
            f3Account.getAccountNumber(),
            PhoneUtil.hideMobile(f3UserRes.getLoginName()) + "商家"
                    + EBizType.AJ_RZJ.getValue());
        Account account = accountBO.getAccountByUserId(userId);
        accountBO.refreshAmount(f3Account.getAccountNumber(), -amount,
            account.getAccountNumber(), EBizType.AJ_RJQ);
        return accountNumber;
    }

    /** 
     * @see com.std.account.ao.IAccountAO#addIntegral(java.lang.String)
     */
    @Override
    @Transactional
    public void addIntegral(String userId) {
        XN805901Res userRes = userBO.getRemoteUser(userId, userId);
        if (userRes == null) {
            throw new BizException("li779001", "用户编号不存在");
        }
        String f3UserId = userRes.getUserReferee();
        XN805901Res f3UserRes = userBO.getRemoteUser(f3UserId, f3UserId);
        if (f3UserRes == null) {
            throw new BizException("li779001", "积分商用户编号不存在");
        }
        Long amount = 0L;
        if (EUserKind.f1.getCode().equals(userRes.getKind())) {
            amount = Long.valueOf(PropertiesUtil.Config.SECOND_INTEGRAL);
        }
        Account account = accountBO.getAccountByUserId(userId);
        Account f3Account = accountBO.getAccountByUserId(f3UserId);
        accountBO.refreshAmount(account.getAccountNumber(), amount,
            f3Account.getAccountNumber(), EBizType.AJ_HDJJF,
            f3UserRes.getRealName() + "活动送积分");
        accountBO.refreshAmount(f3Account.getAccountNumber(), -amount,
            account.getAccountNumber(), EBizType.AJ_HDKJF);
    }

    @Override
    public Paginable<Account> queryAccountPage(int start, int limit,
            Account condition) {
        return accountBO.getPaginable(start, limit, condition);
    }

    @Override
    public Account getAccount(String accountNumber) {
        Account account = accountBO.getAccount(accountNumber);
        if (account == null) {
            throw new BizException("li779001", accountNumber + "账户不存在");
        }
        return account;
    }

    /** 
     * @see com.std.account.ao.IAccountAO#getAccountByUserId(java.lang.String)
     */
    @Override
    public Account getAccountByUserId(String userId) {
        return accountBO.getAccountByUserId(userId);
    }

    /** 
     * @see com.std.account.ao.IAccountAO#getAccountByUserId(java.lang.String, java.lang.String)
     */
    @Override
    public Account getAccountByUser(String userId, String currency) {
        return accountBO.getAccountByUser(userId, currency);
    }
}
