/**
 * @Title AccountAOImpl.java 
 * @Package com.ibis.account.ao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-17 下午6:21:25 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.account.ao.IAccountAO;
import com.std.account.bo.IAccountBO;
import com.std.account.bo.ICQOrderBO;
import com.std.account.bo.IHLOrderBO;
import com.std.account.bo.IXNBOrderBO;
import com.std.account.bo.IZZOrderBO;
import com.std.account.bo.base.Paginable;
import com.std.account.core.StringValidater;
import com.std.account.domain.Account;
import com.std.account.enums.EBizType;
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
    ICQOrderBO cqOrderBO;

    @Autowired
    IHLOrderBO hlOrderBO;

    @Autowired
    IXNBOrderBO xnbOrderBO;

    @Autowired
    IZZOrderBO zzOrderBO;

    @Override
    public Account getAccount(String userId) {
        Account account = accountBO.getAccountByUserId(userId);
        if (account == null) {
            throw new BizException("li779001", userId + "用户不存在");
        }
        return account;
    }

    @Override
    public Paginable<Account> queryAccountPage(int start, int limit,
            Account condition) {
        return accountBO.getPaginable(start, limit, condition);
    }

    /**
     * @see com.std.account.ao.IAccountAO#queryAccountList(com.std.account.domain.Account)
     */
    @Override
    public List<Account> queryAccountList(Account condition) {
        return accountBO.queryAccountList(condition);
    }

    /** 
     * @see com.ibis.account.ao.IAccountAO#isExistAccount(java.lang.String)
     */
    @Override
    public String isExistAccount(String userId) {
        String accountNumber = null;
        Account acount = accountBO.getAccountByUserId(userId);
        if (acount != null) {
            accountNumber = acount.getAccountNumber();
        }
        return accountNumber;
    }

    /** 
     * @see com.ibis.account.ao.IAccountAO#distributeAccount(java.lang.String, java.lang.String)
     */
    @Override
    public String distributeAccount(String userId, String currency) {
        StringValidater.validateBlank(userId);
        if (StringUtils.isBlank(currency)) {// 默认为人民币
            currency = ECurrency.CNY.getCode();
        } else {
            currency = currency.toUpperCase();
            ECurrency c = ECurrency.getCurrencyMap().get(currency);
            if (c == null) {
                throw new BizException("li779001", "currency不在程序所能支持序列");
            }
        }
        return accountBO.distributeAccount(userId, null, currency);
    }

    @Override
    public void doFreeze(String userId, Long amount, String oppositeSystem,
            String oppositeAccount, String remark) {
        EBizType bizType = null;
        Account account = accountBO.getAccountByUserId(userId);
        if (account == null) {
            throw new BizException("li779001", userId + "用户不存在");
        }
        if (amount > 0) {// 解冻
            bizType = EBizType.AJ_JD;
            accountBO.unfreezeAmount(account.getAccountNumber(), amount,
                bizType, oppositeSystem, true);
        } else {// 冻结
            bizType = EBizType.AJ_DJ;
            amount = -amount;
            accountBO.freezeAmount(account.getAccountNumber(), amount, bizType,
                oppositeSystem);
        }

    }
}
