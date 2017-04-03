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
     * 更新户名
     * @param userId
     * @param realName
     * @param systemCode 
     * @create: 2017年1月4日 上午11:41:02 xieyj
     * @history:
     */
    public void editAccountName(String userId, String realName,
            String systemCode);

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
     * 通过橙账本不同币种账户之间划转资金，即以一定的比例内部划转资金
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
            String toAccountNumber, Long transAmount, Double rate,
            String bizType, String bizNote);

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
     * 通过橙账本划转资金，即内部划转资金
     * @param systemCode
     * @param fromUserId
     * @param toUserId
     * @param currency
     * @param transAmount
     * @param bizType
     * @param fromBizNote
     * @param toBizNote 
     * @create: 2017年2月25日 下午12:41:05 xieyj
     * @history:
     */
    void transAmountCZB(String fromUserId, String toUserId, String currency,
            Long transAmount, String bizType, String fromBizNote,
            String toBizNote);

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
     * @param userId
     * @param currency
     * @return 
     * @create: 2016年12月28日 下午2:21:47 xieyj
     * @history:
     */
    public List<Account> getAccountByUserId(String userId, String currency);

    // 商户针对C端手机划转资金
    public void doTransferB2C(String storeOwner, String mobile, Long amount,
            String currency);

    // 加盟商对商户划转资金
    public void doTransferF2B(String franchiseeUser, String storeOwner,
            Long amount, String currency);
}
