package com.std.account.bo;

import java.util.List;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.Account;
import com.std.account.enums.EAccountStatus;
import com.std.account.enums.ECurrency;

/**
 * @author: xieyj
 * @since: 2016年11月11日 上午11:23:06 
 * @history:
 */
public interface IAccountBO extends IPaginableBO<Account> {

    /**
     * 分配账户
     * @param systemCode
     * @param accountName
     * @param type
     * @param realName
     * @param currency
     * @return 
     * @create: 2016年11月11日 上午11:09:36 xieyj
     * @history:
     */
    public String distributeAccount(String systemCode, String accountName,
            String type, String realName, ECurrency currency);

    /**
     * 直接变更账户金额
     * @param accountNumber
     * @param transAmount 发生金额正负
     * @param lastOrder 
     * @create: 2016年11月11日 上午11:17:33 xieyj
     * @history:
     */
    public void refreshAmount(String accountNumber, Long transAmount,
            String lastOrder);

    /**
     * 冻结账户金额
     * @param accountNumber
     * @param freezeAmount 冻结金额正
     * @param lastOrder 
     * @create: 2016年11月11日 上午11:18:36 xieyj
     * @history:
     */
    public void frozenAmount(String accountNumber, Long freezeAmount,
            String lastOrder);

    /**
     * 解冻账户金额
     * @param accountNumber
     * @param unfreezeAmount
     * @param lastOrder
     * @param unfrozenResult(1 解冻成功，冻结金额清零，0 解冻不成功，冻结金额返回)
     * @create: 2016年11月11日 上午11:20:03 xieyj
     * @history:
     */
    public void unfrozenAmount(String accountNumber, Long unfreezeAmount,
            Boolean unfrozenResult, String lastOrder);

    /**
     * 更新账户状态
     * @param accountNumber
     * @param status 
     * @create: 2016年11月11日 上午11:11:04 xieyj
     * @history:
     */
    public void refreshStatus(String accountNumber, EAccountStatus status);

    /**
     * 获取账户
     * @param accountNumber
     * @return 
     * @create: 2016年11月11日 上午10:52:24 xieyj
     * @history:
     */
    public Account getAccount(String accountNumber);

    /**
     * 根据户名获取账户
     * @param systemCode
     * @param accountName
     * @return 
     * @create: 2016年11月11日 上午11:22:48 xieyj
     * @history:
     */
    public Account getAccountByAccountName(String systemCode, String accountName);

    /**
     * 获取账户列表
     * @param data
     * @return 
     * @create: 2016年11月11日 上午10:52:08 xieyj
     * @history:
     */
    public List<Account> queryAccountList(Account data);
}
