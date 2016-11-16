package com.std.account.ao;

import com.std.account.domain.Account;

public interface IAccountAO {

    Account getAccount(String systemCode, String accountName,
            String accountNumber);

    /**
     * 通过橙账本划转资金，即内部划转资金
     * @param systemCode
     * @param fromAccountName
     * @param fromAccountNumber
     * @param toAccountName
     * @param toAccountNumber
     * @param transAmount
     * @param bizType
     * @param bizNote 
     * @create: 2016年11月16日 下午5:03:24 myb858
     * @history:
     */
    void transAmountCZB(String systemCode, String fromAccountName,
            String fromAccountNumber, String toAccountName,
            String toAccountNumber, Long transAmount, String bizType,
            String bizNote);

    /**
     * 通过PC网关划转资金，属于外部划转资金
     * @param systemCode
     * @param companyCode
     * @param accountName
     * @param accountNumber
     * @param transAmount
     * @param bankCode 
     * @create: 2016年11月16日 下午8:16:15 myb858
     * @history:
     */
    void transAmountPC(String systemCode, String companyCode,
            String accountName, String accountNumber, Long transAmount,
            String bankCode);

    /**
     * 通过WAP一键划转资金，属于外部划转资金
     * @param systemCode
     * @param accountName
     * @param accountNumber
     * @param transAmount
     * @param idType
     * @param idNo
     * @param name
     * @param bankCard 
     * @create: 2016年11月16日 下午7:53:42 myb858
     * @history:
     */
    void transAmountWAP(String systemCode, String accountName,
            String accountNumber, Long transAmount, String idType, String idNo,
            String name, String bankCard);

}
