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

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.IAccountBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.common.DateUtil;
import com.std.account.core.AccountUtil;
import com.std.account.core.OrderNoGenerater;
import com.std.account.dao.IAFJourDAO;
import com.std.account.dao.IAJourDAO;
import com.std.account.dao.IAccountDAO;
import com.std.account.domain.Account;
import com.std.account.domain.AccountFrozenJour;
import com.std.account.domain.AccountJour;
import com.std.account.enums.EAccountJourStatus;
import com.std.account.enums.EAccountStatus;
import com.std.account.enums.EBizType;
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

    @Autowired
    private IAFJourDAO afJourDAO;

    @Autowired
    private IAJourDAO aJourDAO;

    @Override
    public String distributeAccount(String userId, String realName,
            String currency) {
        String accountNumber = null;
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(currency)) {
            accountNumber = OrderNoGenerater.generate("A");
            Account data = new Account();
            data.setUserId(userId);
            data.setRealName(realName);
            data.setAccountNumber(accountNumber);
            data.setStatus(EAccountStatus.NORMAL.getCode());
            data.setAmount(0L);

            data.setFrozenAmount(0L);
            data.setCurrency(currency);
            data.setMd5(AccountUtil.md5(data.getAmount()));
            data.setCreateDatetime(new Date());
            accountDAO.insert(data);
        }
        return accountNumber;
    }

    @Override
    public int refreshStatus(String accountNumber, EAccountStatus status) {
        int count = 0;
        if (StringUtils.isNotBlank(accountNumber)) {

            Account data = new Account();
            data.setAccountNumber(accountNumber);
            data.setStatus(status.getCode());
            data.setUpdateDatetime(new Date());
            count = accountDAO.updateStatus(data);

        }
        return count;
    }

    @Override
    public void refreshRealName(String userId, String realName) {
        Account data = new Account();
        data.setUserId(userId);
        data.setRealName(realName);
        data.setUpdateDatetime(new Date());
        accountDAO.updateRealName(data);
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

    /** 
     * @see com.ibis.account.bo.IAccountBO#getAccountByUserId(java.lang.String)
     */
    @Override
    public Account getAccountByUserId(String userId) {
        Account data = null;
        if (StringUtils.isNotBlank(userId)) {
            Account condition = new Account();
            condition.setUserId(userId);
            data = accountDAO.select(condition);
        }
        return data;
    }

    /** 
     * @see com.ibis.account.bo.IAccountBO#queryAccountList(com.ibis.account.domain.Account)
     */
    @Override
    public List<Account> queryAccountList(Account data) {
        return accountDAO.selectList(data);
    }

    /** 
     * @see com.ibis.account.bo.IAccountBO#checkAccount()
     */
    @Override
    public void checkAccount() {
        // TODO Auto-generated method stub

    }

    @Override
    public int refreshAmount(String accountNumber, Long transAmount,
            String bizType, String refNo, String remark) {
        int count = 0;
        if (StringUtils.isNotBlank(accountNumber)) {
            Account dbAccount = this.getAccount(accountNumber);
            Long nowAmount = dbAccount.getAmount() + transAmount;
            if (nowAmount < 0) {
                throw new BizException("li779001", "账户余额不足");
            }
            Account account = new Account();
            account.setAccountNumber(accountNumber);
            account.setAmount(nowAmount);
            account.setMd5(AccountUtil.md5(account.getAmount()));
            account.setUpdateDatetime(new Date());
            count = accountDAO.updateAmount(account);
            // 记录流水
            AccountJour accountJour = new AccountJour();
            accountJour.setStatus(EAccountJourStatus.todoCheck.getCode());
            accountJour.setBizType(bizType);
            accountJour.setRefNo(refNo);
            accountJour.setTransAmount(transAmount);

            accountJour.setPreAmount(dbAccount.getAmount());

            accountJour.setPostAmount(nowAmount);
            accountJour.setRemark(remark);
            accountJour.setCreateDatetime(new Date());
            accountJour.setWorkDate(DateUtil
                .getToday(DateUtil.DB_DATE_FORMAT_STRING));
            accountJour.setAccountNumber(accountNumber);
            aJourDAO.insert(accountJour);
        }
        return count;
    }

    @Override
    public int refreshAmountWithoutCheck(String accountNumber,
            Long transAmount, String bizType, String refNo) {
        int count = 0;
        if (StringUtils.isNotBlank(accountNumber)) {
            Account dbAccount = this.getAccount(accountNumber);
            Long nowAmount = dbAccount.getAmount() + transAmount;
            if (nowAmount < 0) {
                throw new BizException("li779001", "账户余额不足");
            }
            Account account = new Account();
            account.setAccountNumber(accountNumber);
            account.setAmount(nowAmount);
            account.setMd5(AccountUtil.md5(account.getAmount()));
            account.setUpdateDatetime(new Date());
            count = accountDAO.updateAmount(account);
            // 记录流水
            AccountJour accountJour = new AccountJour();
            accountJour.setStatus(EAccountJourStatus.Done.getCode());
            accountJour.setBizType(bizType);
            accountJour.setRefNo(refNo);
            accountJour.setTransAmount(transAmount);

            accountJour.setPreAmount(dbAccount.getAmount());

            accountJour.setPostAmount(nowAmount);
            accountJour.setRemark(EBizType.getBizTypeMap().get(bizType)
                .getValue());
            accountJour.setCreateDatetime(new Date());
            accountJour.setWorkDate(DateUtil
                .getToday(DateUtil.DB_DATE_FORMAT_STRING));
            accountJour.setAccountNumber(accountNumber);
            aJourDAO.insert(accountJour);
        }
        return count;
    }

    @Override
    public void freezeAmount(String accountNumber, Long freezeAmount,
            EBizType bizType, String refNo) {
        if (freezeAmount < 0) {
            throw new BizException("xn702000", "冻结金额不能小于0");
        }
        if (StringUtils.isNotBlank(accountNumber)) {
            Account dbAccount = this.getAccount(accountNumber);
            Long nowAmount = dbAccount.getAmount() - freezeAmount;
            if (nowAmount < 0) {
                throw new BizException("xn702000", "账户余额不足");
            }
            Long nowFrozenAmount = dbAccount.getFrozenAmount() + freezeAmount;
            Date now = new Date();
            Account account = new Account();
            account.setAccountNumber(accountNumber);
            account.setAmount(nowAmount);
            account.setFrozenAmount(nowFrozenAmount);
            account.setMd5(AccountUtil.md5(account.getAmount()));
            account.setUpdateDatetime(now);
            accountDAO.updateFrozenAmount(account);
            // 记录资金流水
            AccountJour accountJour = new AccountJour();
            accountJour.setStatus(EAccountJourStatus.todoCheck.getCode());
            accountJour.setBizType(bizType.getCode());
            accountJour.setRefNo(refNo);
            accountJour.setTransAmount(-freezeAmount);
            accountJour.setPreAmount(dbAccount.getAmount());

            accountJour.setPostAmount(nowAmount);
            accountJour.setRemark(bizType.getValue());
            accountJour.setCreateDatetime(new Date());
            accountJour.setWorkDate(DateUtil
                .getToday(DateUtil.DB_DATE_FORMAT_STRING));
            accountJour.setAccountNumber(accountNumber);
            aJourDAO.insert(accountJour);
            // 记录冻结流水
            AccountFrozenJour accountFrozenJour = new AccountFrozenJour();
            accountFrozenJour.setBizType(bizType.getCode());
            accountFrozenJour.setRefNo(refNo);
            accountFrozenJour.setTransAmount(freezeAmount);
            accountFrozenJour.setPreAmount(dbAccount.getFrozenAmount());

            accountFrozenJour.setPostAmount(nowFrozenAmount);
            accountFrozenJour.setRemark(bizType.getValue());
            accountFrozenJour.setCreateDatetime(now);
            accountFrozenJour.setAccountNumber(accountNumber);
            afJourDAO.insert(accountFrozenJour);
        }
    }

    @Override
    public void unfreezeAmount(String accountNumber, Long unfreezeAmount,
            EBizType bizType, String refNo, boolean backFlag) {
        if (unfreezeAmount < 0) {
            throw new BizException("xn702000", "解结金额不能小于0");
        }

        if (StringUtils.isNotBlank(accountNumber)) {
            Account dbAccount = this.getAccount(accountNumber);
            Long nowFrozenAmount = dbAccount.getFrozenAmount() - unfreezeAmount;
            if (nowFrozenAmount < 0) {
                throw new BizException("xn702000", "本次账户解冻金额，会使账户冻结金额小于0");
            }
            if (backFlag) {// 解冻的金额返回至余额中
                Long nowAmount = dbAccount.getAmount() + unfreezeAmount;
                Date now = new Date();
                Account account = new Account();
                account.setAccountNumber(accountNumber);
                account.setAmount(nowAmount);
                account.setFrozenAmount(nowFrozenAmount);
                account.setMd5(AccountUtil.md5(account.getAmount()));
                account.setUpdateDatetime(now);
                accountDAO.updateFrozenAmount(account);
                // 记录资金流水
                AccountJour accountJour = new AccountJour();
                accountJour.setStatus(EAccountJourStatus.todoCheck.getCode());
                accountJour.setBizType(bizType.getCode());
                accountJour.setRefNo(refNo);
                accountJour.setTransAmount(unfreezeAmount);
                accountJour.setPreAmount(dbAccount.getAmount());

                accountJour.setPostAmount(nowAmount);
                accountJour.setRemark("解冻金额返还回余额");
                accountJour.setCreateDatetime(new Date());
                accountJour.setWorkDate(DateUtil
                    .getToday(DateUtil.DB_DATE_FORMAT_STRING));
                accountJour.setAccountNumber(accountNumber);
                aJourDAO.insert(accountJour);
                // 记录冻结流水
                AccountFrozenJour accountFrozenJour = new AccountFrozenJour();
                accountFrozenJour.setBizType(bizType.getCode());
                accountFrozenJour.setRefNo(refNo);
                accountFrozenJour.setTransAmount(-unfreezeAmount);
                accountFrozenJour.setPreAmount(dbAccount.getFrozenAmount());

                accountFrozenJour.setPostAmount(nowFrozenAmount);
                accountFrozenJour.setRemark("解冻金额返还回余额");
                accountFrozenJour.setCreateDatetime(now);
                accountFrozenJour.setAccountNumber(accountNumber);
                afJourDAO.insert(accountFrozenJour);
            } else {
                Long nowAmount = dbAccount.getAmount();
                Date now = new Date();
                Account account = new Account();
                account.setAccountNumber(accountNumber);
                account.setAmount(nowAmount);
                account.setFrozenAmount(nowFrozenAmount);
                account.setMd5(AccountUtil.md5(account.getAmount()));
                account.setUpdateDatetime(now);
                accountDAO.updateFrozenAmount(account);
                // 记录流水
                AccountFrozenJour accountFrozenJour = new AccountFrozenJour();
                accountFrozenJour.setBizType(bizType.getCode());
                accountFrozenJour.setRefNo(refNo);
                accountFrozenJour.setTransAmount(-unfreezeAmount);
                accountFrozenJour.setPreAmount(dbAccount.getFrozenAmount());

                accountFrozenJour.setPostAmount(nowFrozenAmount);
                accountFrozenJour.setRemark("解冻金额直接扣减使用");
                accountFrozenJour.setCreateDatetime(now);
                accountFrozenJour.setAccountNumber(accountNumber);
                afJourDAO.insert(accountFrozenJour);
            }

        }
    }

}
