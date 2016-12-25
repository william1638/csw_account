package com.std.account.ao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.account.ao.IAccountAO;
import com.std.account.bo.IAccountBO;
import com.std.account.bo.ICompanyChannelBO;
import com.std.account.bo.IJourBO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.Account;
import com.std.account.enums.EAccountType;
import com.std.account.enums.EChannelType;
import com.std.account.exception.BizException;

@Service
public class AccountAOImpl implements IAccountAO {
    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private ICompanyChannelBO companyChannelBO;

    @Autowired
    private IJourBO jourBO;

    /** 
     * @see com.std.account.ao.IAccountAO#distributeAccount(java.lang.String, java.lang.String, com.std.account.enums.EAccountType, java.util.List, java.lang.String)
     */
    @Override
    @Transactional
    public void distributeAccount(String userId, String realName,
            String accountType, List<String> currencyList, String systemCode) {
        if (CollectionUtils.isNotEmpty(currencyList)) {
            Map<String, EAccountType> map = EAccountType
                .getAccountTypeResultMap();
            EAccountType eAccountType = map.get(accountType);
            if (null == eAccountType) {
                new BizException("XN0000", "账户类型不存在");
            }
            for (String currency : currencyList) {
                accountBO.distributeAccount(userId, realName, eAccountType,
                    currency, systemCode);
            }
        }
    }

    @Override
    @Transactional
    public void transAmountCZB(String systemCode, String fromAccountNumber,
            String toAccountNumber, Long transAmount, String bizType,
            String bizNote) {
        String fromBizNote = bizNote + "(去方账号[" + toAccountNumber + "])";
        accountBO.transAmount(systemCode, fromAccountNumber, EChannelType.CZB,
            null, -transAmount, bizType, fromBizNote);
        String toBizNote = bizNote + "(来方账号[" + fromAccountNumber + "])";
        accountBO.transAmount(systemCode, toAccountNumber, EChannelType.CZB,
            null, transAmount, bizType, toBizNote);
    }

    @Override
    @Transactional
    public void transAmountPC(String systemCode, String companyCode,
            String accountName, String accountNumber, Long transAmount,
            String bankCode) {
        // // 智能路由
        // EChannelType channelType =
        // companyChannelBO.getBestChannel(companyCode,
        // EPayType.PC);
        // // 调用对应渠道，进行资金划转申请
        // Account dbAccount = accountBO.getAccount(systemCode, accountNumber);
        // EBizType bizType = EBizType.AJ_CZ;
        // if (transAmount < 0) {
        // bizType = EBizType.AJ_QX;
        // }
        // String order = jourBO.addTochangeJour(systemCode, accountName,
        // accountNumber, channelType, EPayType.PC, bizType,
        // dbAccount.getAmount(), transAmount);
        // companyChannelBO.transAmountPC(companyCode, channelType, EPayType.PC,
        // transAmount, order, bankCode);
    }

    @Override
    @Transactional
    public void transAmountWAP(String systemCode, String accountName,
            String accountNumber, Long transAmount, String idType, String idNo,
            String name, String bankCard) {
        // 智能路由
        // 调用对应渠道，进行资金划转。

    }

    @Override
    public Account getAccount(String systemCode, String accountNumber) {
        return accountBO.getAccount(systemCode, accountNumber);
    }

    /** 
     * @see com.std.account.ao.IAccountAO#queryAccountPage(int, int, com.std.account.domain.Account)
     */
    @Override
    public Paginable<Account> queryAccountPage(int start, int limit,
            Account condition) {
        return accountBO.getPaginable(start, condition);
    }

    /** 
     * @see com.std.account.ao.IAccountAO#queryAccountList(com.std.account.domain.Account)
     */
    @Override
    public List<Account> queryAccountList(Account condition) {
        return accountBO.queryAccountList(condition);
    }

    /** 
     * @see com.std.account.ao.IAccountAO#getAccountByUserId(java.lang.String, java.lang.String)
     */
    @Override
    public List<Account> getAccountByUserId(String systemCode, String userId) {
        Account condition = new Account();
        condition.setSystemCode(systemCode);
        condition.setUserId(userId);
        return accountBO.queryAccountList(condition);
    }
}
