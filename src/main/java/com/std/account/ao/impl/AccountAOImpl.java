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

import com.std.account.ao.IAccountAO;
import com.std.account.bo.IAccountBO;
import com.std.account.bo.IHLOrderBO;
import com.std.account.bo.IWithdrawBO;
import com.std.account.bo.IZZOrderBO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.Account;
import com.std.account.enums.ECurrency;
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
    IWithdrawBO withdrawBO;

    @Autowired
    IHLOrderBO hlOrderBO;

    @Autowired
    IZZOrderBO zzOrderBO;

    @Override
    public String distributeAccount(String userId, String realName,
            ECurrency currency) {
        return accountBO.distributeAccount(userId, realName, currency);
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

}
