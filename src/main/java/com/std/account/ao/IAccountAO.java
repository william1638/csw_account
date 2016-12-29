package com.std.account.ao;

import java.util.List;

import com.std.account.bo.base.Paginable;
import com.std.account.domain.Account;

public interface IAccountAO {
    String DEFAULT_ORDER_COLUMN = "account_number";

    /**
     * 个人创建多账户
     * @param userId
     * @param realName
     * @param accountType
     * @param currency
     * @param systemCode
     * @return 
     * @create: 2016年12月23日 下午7:52:47 xieyj
     * @history:
     */
    public void distributeAccount(String userId, String realName,
            String accountType, List<String> currencyList, String systemCode);

    /**
     * 通过橙账本划转资金，即内部划转资金
     * @param systemCode
     * @param fromAccountNumber
     * @param toAccountNumber
     * @param transAmount
     * @param bizType
     * @param bizNote 
     * @create: 2016年12月25日 下午3:16:12 xieyj
     * @history:
     */
    void transAmountCZB(String systemCode, String fromAccountNumber,
            String toAccountNumber, Long transAmount, String bizType,
            String bizNote);

    /**
     * 通过用户编号以及指定币种进行账户划转
     * @param systemCode
     * @param fromUserId
     * @param toUserId
     * @param currency
     * @param transAmount
     * @param bizType
     * @param bizNote 
     * @create: 2016年12月28日 下午1:53:12 xieyj
     * @history:
     */
    void transAmountCZB(String systemCode, String fromUserId, String toUserId,
            String currency, Long transAmount, String bizType, String bizNote);

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

    /**
     * 分页查询账户
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2015年6月26日 下午4:04:11 myb858
     * @history:
     */
    public Paginable<Account> queryAccountPage(int start, int limit,
            Account condition);

    /**
     * 列表查询账户
     * @param condition
     * @return 
     * @create: 2015年6月26日 下午4:04:11 myb858
     * @history:
     */
    public List<Account> queryAccountList(Account condition);

    /**
     * 根据accountNumber查询账户
     * @param systemCode
     * @param accountNumber
     * @return 
     * @create: 2016年12月23日 下午6:48:33 xieyj
     * @history:
     */
    public Account getAccount(String systemCode, String accountNumber);

    /**
     * 根据用户编号,币种获取账户列表
     * @param systemCode
     * @param userId
     * @param currency
     * @return 
     * @create: 2016年12月28日 下午2:21:47 xieyj
     * @history:
     */
    public List<Account> getAccountByUserId(String systemCode, String userId,
            String currency);
}
