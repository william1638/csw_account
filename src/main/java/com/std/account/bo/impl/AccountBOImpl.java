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
import com.std.account.enums.ECurrency;
import com.std.account.enums.EUser;
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
            ECurrency currency) {
        String accountNumber = null;
        if (StringUtils.isNotBlank(userId)) {
            Account data = new Account();
            if (ECurrency.XNB.equals(currency)) {
                accountNumber = userId;
            } else {
                accountNumber = OrderNoGenerater.generate("U");
            }
            data.setAccountNumber(accountNumber);
            data.setUserId(userId);
            data.setRealName(realName);
            data.setCurrency(currency.getCode());
            data.setAmount(0L);

            data.setFrozenAmount(0L);
            data.setMd5(AccountUtil.md5(data.getAmount()));
            data.setStatus(EAccountStatus.NORMAL.getCode());
            data.setCreateDatetime(new Date());
            accountDAO.insert(data);
        } else {
            throw new BizException("xn702000", "入参错误");
        }
        return accountNumber;
    }

    @Override
    public String distributeAccount(String userId, String realName,
            ECurrency currency, Long amount) {
        String accountNumber = null;
        if (StringUtils.isNotBlank(userId)) {
            Account data = new Account();
            if (ECurrency.XNB.equals(currency)) {
                accountNumber = userId;
            } else {
                accountNumber = OrderNoGenerater.generate("A");
            }
            data.setAccountNumber(accountNumber);
            data.setUserId(userId);
            data.setRealName(realName);
            data.setCurrency(currency.getCode());
            data.setAmount(amount);

            data.setFrozenAmount(0L);
            data.setMd5(AccountUtil.md5(data.getAmount()));
            data.setStatus(EAccountStatus.NORMAL.getCode());
            data.setCreateDatetime(new Date());
            accountDAO.insert(data);
        } else {
            throw new BizException("xn702000", "入参错误");
        }
        return accountNumber;
    }

    @Override
    public void refreshAmount(String accountNumber, Long transAmount,
            String refNo, EBizType bizType) {
        Account dbAccount = this.getAccount(accountNumber);
        Long nowAmount = dbAccount.getAmount() + transAmount;
        if (nowAmount < 0) {
            throw new BizException("li779001", "账户余额不足");
        }
        Date now = new Date();
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setAmount(nowAmount);
        account.setMd5(AccountUtil.md5(account.getAmount()));
        account.setUpdateDatetime(now);
        accountDAO.updateAmount(account);
        // 记录流水
        AccountJour accountJour = new AccountJour();
        accountJour.setBizType(bizType.getCode());
        accountJour.setRefNo(refNo);
        accountJour.setTransAmount(transAmount);
        accountJour.setPreAmount(dbAccount.getAmount());
        accountJour.setPostAmount(nowAmount);

        accountJour.setRemark(bizType.getValue());
        accountJour.setCreateDatetime(now);
        accountJour.setAccountNumber(accountNumber);
        accountJour.setStatus(EAccountJourStatus.todoCheck.getCode());
        accountJour.setWorkDate(DateUtil
            .getToday(DateUtil.DB_DATE_FORMAT_STRING));
        aJourDAO.insert(accountJour);

    }

    @Override
    public void refreshAmount(String accountNumber, Long transAmount,
            String refNo, EBizType bizType, String remark) {
        Account dbAccount = this.getAccount(accountNumber);
        Long nowAmount = dbAccount.getAmount() + transAmount;
        if (nowAmount < 0) {
            throw new BizException("li779001", "账户余额不足");
        }
        Date now = new Date();
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setAmount(nowAmount);
        account.setMd5(AccountUtil.md5(account.getAmount()));
        account.setUpdateDatetime(now);
        accountDAO.updateAmount(account);
        // 记录流水
        AccountJour accountJour = new AccountJour();
        accountJour.setBizType(bizType.getCode());
        accountJour.setRefNo(refNo);
        accountJour.setTransAmount(transAmount);
        accountJour.setPreAmount(dbAccount.getAmount());
        accountJour.setPostAmount(nowAmount);

        accountJour.setRemark(remark);
        accountJour.setCreateDatetime(now);
        accountJour.setAccountNumber(accountNumber);
        accountJour.setStatus(EAccountJourStatus.todoCheck.getCode());
        accountJour.setWorkDate(DateUtil
            .getToday(DateUtil.DB_DATE_FORMAT_STRING));
        aJourDAO.insert(accountJour);

    }

    @Override
    public void refreshAmountWithoutCheck(String accountNumber,
            Long transAmount, String refNo, EBizType bizType) {
        Account dbAccount = this.getAccount(accountNumber);
        Long nowAmount = dbAccount.getAmount() + transAmount;
        if (nowAmount < 0) {
            throw new BizException("li779001", "账户余额不足");
        }
        Date now = new Date();
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setAmount(nowAmount);
        account.setMd5(AccountUtil.md5(account.getAmount()));
        account.setUpdateDatetime(now);
        accountDAO.updateAmount(account);
        // 记录流水
        AccountJour accountJour = new AccountJour();
        accountJour.setBizType(bizType.getCode());
        accountJour.setRefNo(refNo);
        accountJour.setTransAmount(transAmount);
        accountJour.setPreAmount(dbAccount.getAmount());
        accountJour.setPostAmount(nowAmount);

        accountJour.setRemark(bizType.getValue());
        accountJour.setCreateDatetime(now);
        accountJour.setAccountNumber(accountNumber);
        accountJour.setStatus(EAccountJourStatus.noCheck.getCode());
        accountJour.setWorkDate(DateUtil
            .getToday(DateUtil.DB_DATE_FORMAT_STRING));

        accountJour.setCheckUser(EUser.LI.getCode());
        accountJour.setCheckDatetime(now);
        aJourDAO.insert(accountJour);

    }

    @Override
    public void freezeAmount(String accountNumber, Long freezeAmount,
            String refNo, EBizType bizType) {
        if (freezeAmount < 0) {
            throw new BizException("xn702000", "冻结积分不能小于0");
        }
        Account dbAccount = this.getAccount(accountNumber);
        Long nowAmount = dbAccount.getAmount() - freezeAmount;
        if (nowAmount < 0) {
            throw new BizException("xn702000", "账户积分不足");
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
        accountJour.setBizType(bizType.getCode());
        accountJour.setRefNo(refNo);
        accountJour.setTransAmount(-freezeAmount);
        accountJour.setPreAmount(dbAccount.getAmount());
        accountJour.setPostAmount(nowAmount);

        accountJour.setRemark(bizType.getValue());
        accountJour.setCreateDatetime(new Date());
        accountJour.setAccountNumber(accountNumber);
        accountJour.setStatus(EAccountJourStatus.todoCheck.getCode());
        accountJour.setWorkDate(DateUtil
            .getToday(DateUtil.DB_DATE_FORMAT_STRING));
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

    @Override
    public void unfreezeAmount(String accountNumber, Long unfreezeAmount,
            String refNo, EBizType bizType) {
        if (unfreezeAmount < 0) {
            throw new BizException("xn702000", "解结积分不能小于0");
        }
        Account dbAccount = this.getAccount(accountNumber);
        Long nowFrozenAmount = dbAccount.getFrozenAmount() - unfreezeAmount;
        if (nowFrozenAmount < 0) {
            throw new BizException("xn702000", "本次账户解冻积分，会使账户冻结积分小于0");
        }
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

        accountFrozenJour.setRemark(bizType.getValue());
        accountFrozenJour.setCreateDatetime(now);
        accountFrozenJour.setAccountNumber(accountNumber);
        afJourDAO.insert(accountFrozenJour);

    }

    @Override
    public void unfreezeAmountToAmount(String accountNumber,
            Long unfreezeAmount, String refNo, EBizType bizType) {
        if (unfreezeAmount < 0) {
            throw new BizException("xn702000", "解结积分不能小于0");
        }
        Account dbAccount = this.getAccount(accountNumber);
        Long nowFrozenAmount = dbAccount.getFrozenAmount() - unfreezeAmount;
        if (nowFrozenAmount < 0) {
            throw new BizException("xn702000", "本次账户解冻积分，会使账户冻结积分小于0");
        }
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
        accountJour.setBizType(bizType.getCode());
        accountJour.setRefNo(refNo);
        accountJour.setTransAmount(unfreezeAmount);
        accountJour.setPreAmount(dbAccount.getAmount());
        accountJour.setPostAmount(nowAmount);

        accountJour.setRemark(bizType.getCode());
        accountJour.setCreateDatetime(new Date());
        accountJour.setStatus(EAccountJourStatus.todoCheck.getCode());
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

        accountFrozenJour.setRemark(bizType.getValue());
        accountFrozenJour.setCreateDatetime(now);
        accountFrozenJour.setAccountNumber(accountNumber);
        afJourDAO.insert(accountFrozenJour);

    }

    @Override
    public void refreshStatus(String accountNumber, EAccountStatus status) {
        Account data = new Account();
        data.setAccountNumber(accountNumber);
        data.setStatus(status.getCode());
        data.setUpdateDatetime(new Date());
        accountDAO.updateStatus(data);
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
    public List<Account> queryAccountList(Account data) {
        return accountDAO.selectList(data);
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
    public Account getAccountByUserId(String userId) {
        Account data = null;
        if (StringUtils.isNotBlank(userId)) {
            Account condition = new Account();
            condition.setUserId(userId);
            data = accountDAO.select(condition);
        }
        return data;
    }

    @Override
    public Account getAccountByUser(String userId, String currency) {
        Account data = null;
        if (StringUtils.isNotBlank(userId)) {
            Account condition = new Account();
            condition.setUserId(userId);
            condition.setCurrency(currency);
            data = accountDAO.select(condition);
            if (data == null) {
                throw new BizException("xn702502", "无对应账户，请检查账号正确性");
            }
        }
        return data;
    }

}
