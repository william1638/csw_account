/**
 * @Title AccountBOImpl.java 
 * @Package com.ibis.account.bo.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午3:21:38 
 * @version V1.0   
 */
package com.std.account.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.IAccountBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.core.AccountUtil;
import com.std.account.core.OrderNoGenerater;
import com.std.account.dao.IAccountDAO;
import com.std.account.domain.Account;
import com.std.account.enums.EAccountStatus;
import com.std.account.enums.EAccountType;
import com.std.account.enums.EBoolean;
import com.std.account.enums.ECurrency;
import com.std.account.enums.EGeneratePrefix;
import com.std.account.exception.BizException;

/** 
 * @author: miyb 
 * @since: 2015-3-15 下午3:21:38 
 * @history:
 */
@Component
public class AccountBOImpl extends PaginableBOImpl<Account> implements
        IAccountBO {
    @Autowired
    private IAccountDAO accountDAO;

    @Override
    public String distributeAccount(String systemCode, String accountName,
            EAccountType accountType, ECurrency currency) {
        String accountNumber = null;
        if (StringUtils.isNotBlank(systemCode)
                && StringUtils.isNotBlank(accountName)) {
            accountNumber = OrderNoGenerater.generate(EGeneratePrefix.Account
                .getCode());
            Account data = new Account();
            data.setSystemCode(systemCode);
            data.setAccountName(accountName);
            data.setAccountNumber(accountNumber);
            data.setType(accountType.getCode());

            data.setStatus(EAccountStatus.NORMAL.getCode());
            data.setCurrency(currency.getCode());
            data.setAmount(0L);
            data.setFrozenAmount(0L);
            data.setMd5(AccountUtil.md5(data.getAmount()));

            data.setCreateDatetime(new Date());
            accountDAO.insert(data);
        }
        return accountNumber;
    }

    @Override
    public void refreshAmount(String accountNumber, Long transAmount,
            String lastOrder) {
        if (StringUtils.isNotBlank(accountNumber)) {
            Account dbAccount = this.getAccount(accountNumber);
            Long nowAmount = dbAccount.getAmount() + transAmount;
            if (nowAmount < 0) {
                throw new BizException("xn000000", "账户余额不足");
            }
            Account data = new Account();
            data.setAccountNumber(accountNumber);
            data.setAmount(nowAmount);
            data.setMd5(AccountUtil.md5(dbAccount.getAmount()));
            data.setLastOrder(lastOrder);
            accountDAO.updateAmount(data);
        }
    }

    @Override
    public void frozenAmount(String accountNumber, Long freezeAmount,
            String lastOrder) {
        if (StringUtils.isNotBlank(accountNumber)) {
            if (freezeAmount <= 0) {
                throw new BizException("xn000000", "冻结金额需大于0");
            }
            Account dbAccount = this.getAccount(accountNumber);
            Long nowAmount = dbAccount.getAmount() - freezeAmount;
            if (nowAmount < 0) {
                throw new BizException("xn000000", "账户余额不足");
            }
            Long nowFrozenAmount = dbAccount.getFrozenAmount() + freezeAmount;
            Account data = new Account();
            data.setAccountNumber(accountNumber);
            data.setAmount(nowAmount);
            data.setFrozenAmount(nowFrozenAmount);
            data.setMd5(AccountUtil.md5(dbAccount.getAmount()));
            data.setLastOrder(lastOrder);
            accountDAO.updateFrozenAmount(data);
        }
    }

    @Override
    public void unfrozenAmount(String accountNumber, Long unfreezeAmount,
            Boolean unfrozenResult, String lastOrder) {
        if (StringUtils.isNotBlank(accountNumber)) {
            if (unfreezeAmount <= 0) {
                throw new BizException("xn000000", "解冻金额需大于0");
            }
            Account dbAccount = this.getAccount(accountNumber);
            Long nowAmount = dbAccount.getAmount();
            if (EBoolean.NO.getCode().equals(unfrozenResult)) {
                nowAmount = nowAmount + unfreezeAmount;
            }
            Long nowFrozenAmount = dbAccount.getFrozenAmount() - unfreezeAmount;
            if (nowFrozenAmount < 0) {
                throw new BizException("xn000000", "本次解冻会使账户冻结金额小于0");
            }
            Account data = new Account();
            data.setAccountNumber(accountNumber);
            data.setAmount(nowAmount);
            data.setFrozenAmount(nowFrozenAmount);
            data.setMd5(AccountUtil.md5(dbAccount.getAmount()));
            data.setLastOrder(lastOrder);
            accountDAO.updateFrozenAmount(data);
        }
    }

    @Override
    public void refreshStatus(String accountNumber, EAccountStatus status) {
        if (StringUtils.isNotBlank(accountNumber)) {
            Account data = new Account();
            data.setAccountNumber(accountNumber);
            data.setStatus(status.getCode());
            accountDAO.updateStatus(data);
        }
    }

    @Override
    public Account getAccount(String accountNumber) {
        Account data = null;
        if (StringUtils.isNotBlank(accountNumber)) {
            Account condition = new Account();
            condition.setAccountNumber(accountNumber);
            data = accountDAO.select(condition);
            if (data == null) {
                throw new BizException("xn702502", "无对应账户，请检查账号正确性");
            }
        }
        return data;
    }

    @Override
    public Account getAccountByAccountName(String systemCode, String accountName) {
        Account condition = new Account();
        condition.setSystemCode(systemCode);
        condition.setAccountName(accountName);
        List<Account> accountList = accountDAO.selectList(condition);
        Account data = null;
        if (CollectionUtils.isEmpty(accountList)) {
            throw new BizException("xn702502", "无对应账户，请检查户名正确性");
        } else {
            data = accountList.get(0);
        }
        return data;
    }

    @Override
    public List<Account> queryAccountList(Account data) {
        return accountDAO.selectList(data);
    }
}
