package com.std.account.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.account.ao.IAccountAO;
import com.std.account.bo.IAccountBO;
import com.std.account.bo.ICompanyChannelBO;
import com.std.account.bo.IJourBO;
import com.std.account.domain.Account;
import com.std.account.enums.EBizType;
import com.std.account.enums.EChannelType;

@Service
public class AccountAOImpl implements IAccountAO {
    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private ICompanyChannelBO companyChannelBO;

    @Autowired
    private IJourBO jourBO;

    @Override
    public Account getAccount(String systemCode, String accountName,
            String accountNumber) {
        return accountBO.getAccount(systemCode, accountNumber);
    }

    @Override
    @Transactional
    public void transAmountCZB(String systemCode, String fromAccountName,
            String fromAccountNumber, String toAccountName,
            String toAccountNumber, Long transAmount, String bizType,
            String bizNote) {
        accountBO.transAmount(systemCode, fromAccountName, fromAccountNumber,
            EChannelType.CZB, EPayType.CZB, -transAmount, bizType, bizNote);
        accountBO.transAmount(systemCode, toAccountName, toAccountNumber,
            EChannelType.CZB, EPayType.CZB, transAmount, bizType, bizNote);

    }

    @Override
    @Transactional
    public void transAmountPC(String systemCode, String companyCode,
            String accountName, String accountNumber, Long transAmount,
            String bankCode) {
        // 智能路由
        EChannelType channelType = companyChannelBO.getBestChannel(companyCode,
            EPayType.PC);
        // 调用对应渠道，进行资金划转申请
        Account dbAccount = accountBO.getAccount(systemCode, accountName,
            accountNumber);
        EBizType bizType = EBizType.AJ_CZ;
        if (transAmount < 0) {
            bizType = EBizType.AJ_QX;
        }
        String order = jourBO.addTochangeJour(systemCode, accountName,
            accountNumber, channelType, EPayType.PC, bizType,
            dbAccount.getAmount(), transAmount);
        companyChannelBO.transAmountPC(companyCode, channelType, EPayType.PC,
            transAmount, order, bankCode);
    }

    @Override
    @Transactional
    public void transAmountWAP(String systemCode, String accountName,
            String accountNumber, Long transAmount, String idType, String idNo,
            String name, String bankCard) {
        // 智能路由
        // 调用对应渠道，进行资金划转。

    }

}
