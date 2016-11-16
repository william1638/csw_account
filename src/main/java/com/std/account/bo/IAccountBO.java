package com.std.account.bo;

import java.util.List;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.Account;
import com.std.account.enums.EAccountStatus;
import com.std.account.enums.EAccountType;
import com.std.account.enums.EChannelType;
import com.std.account.enums.ECurrency;
import com.std.account.enums.EPayType;

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
     * @param accountType
     * @param currency
     * @return 
     * @create: 2016年11月11日 上午11:09:36 xieyj
     * @history:
     */
    public String distributeAccount(String systemCode, String accountName,
            EAccountType accountType, ECurrency currency);

    /**
     * 内部账划拨
     * @param systemCode
     * @param accountName
     * @param accountNumber
     * @param channelType
     * @param payType
     * @param transAmount
     * @param bizType
     * @param bizNote 
     * @create: 2016年11月16日 下午5:49:19 myb858
     * @history:
     */
    public void transAmount(String systemCode, String accountName,
            String accountNumber, EChannelType channelType, EPayType payType,
            Long transAmount, String bizType, String bizNote);

    /**
     * 冻结账户金额
     * @param accountNumber
     * @param freezeAmount 冻结金额正
     * @param lastOrder 
     * @create: 2016年11月11日 上午11:18:36 xieyj
     * @history:
     */
    public void frozenAmount(String systemCode, String accountName,
            String accountNumber, Long freezeAmount, String lastOrder);

    /**
     * 解冻账户金额
     * @param accountNumber
     * @param unfreezeAmount
     * @param lastOrder
     * @param unfrozenResult(1 解冻成功，冻结金额清零，0 解冻不成功，冻结金额返回)
     * @create: 2016年11月11日 上午11:20:03 xieyj
     * @history:
     */
    public void unfrozenAmount(String systemCode, String accountName,
            String accountNumber, Long unfreezeAmount, Boolean unfrozenResult,
            String lastOrder);

    /**
     * 更新账户状态
     * @param accountNumber
     * @param status 
     * @create: 2016年11月11日 上午11:11:04 xieyj
     * @history:
     */
    public void refreshStatus(String systemCode, String accountName,
            String accountNumber, EAccountStatus status);

    /**
     * 获取账户
     * @param systemCode
     * @param accountName
     * @param accountNumber
     * @return 
     * @create: 2016年11月16日 下午4:46:39 myb858
     * @history:
     */
    public Account getAccount(String systemCode, String accountName,
            String accountNumber);

    /**
     * 获取账户列表
     * @param data
     * @return 
     * @create: 2016年11月11日 上午10:52:08 xieyj
     * @history:
     */
    public List<Account> queryAccountList(Account data);
}
