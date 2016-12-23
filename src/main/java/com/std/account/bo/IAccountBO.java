package com.std.account.bo;

import java.util.List;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.Account;
import com.std.account.enums.EAccountStatus;
import com.std.account.enums.EAccountType;
import com.std.account.enums.EChannelType;

/**
 * @author: xieyj
 * @since: 2016年11月11日 上午11:23:06 
 * @history:
 */
public interface IAccountBO extends IPaginableBO<Account> {

    /**
     * 分配账户
     * @param userId
     * @param realName
     * @param accountType
     * @param currency
     * @param systemCode
     * @return 
     * @create: 2016年12月23日 下午12:35:22 xieyj
     * @history:
     */
    public String distributeAccount(String userId, String realName,
            EAccountType accountType, String currency, String systemCode);

    /**
     * 内部账划拨
     * @param systemCode
     * @param accountNumber
     * @param channelType
     * @param channelOrder
     * @param transAmount
     * @param bizType
     * @param bizNote 
     * @create: 2016年12月23日 下午5:58:06 xieyj
     * @history:
     */
    public void transAmount(String systemCode, String accountNumber,
            EChannelType channelType, String channelOrder, Long transAmount,
            String bizType, String bizNote);

    /**
     * 冻结账户金额
     * @param systemCode
     * @param accountNumber
     * @param freezeAmount
     * @param lastOrder 
     * @create: 2016年12月23日 下午5:25:55 xieyj
     * @history:
     */
    public void frozenAmount(String systemCode, String accountNumber,
            Long freezeAmount, String lastOrder);

    /**
     * 解冻账户金额
     * @param systemCode
     * @param accountNumber
     * @param unfreezeAmount
     * @param lastOrder 
     * @create: 2016年12月23日 下午5:26:20 xieyj
     * @history:
     */
    public void unfrozenAmount(String systemCode, String accountNumber,
            Long unfreezeAmount, String lastOrder);

    /**
     * 更新账户状态
     * @param systemCode
     * @param accountNumber
     * @param status 
     * @create: 2016年12月23日 下午5:27:04 xieyj
     * @history:
     */
    public void refreshStatus(String systemCode, String accountNumber,
            EAccountStatus status);

    /**
     * 获取账户
     * @param systemCode
     * @param accountNumber
     * @return 
     * @create: 2016年12月23日 下午5:27:22 xieyj
     * @history:
     */
    public Account getAccount(String systemCode, String accountNumber);

    /**
     * 获取账户列表
     * @param data
     * @return 
     * @create: 2016年11月11日 上午10:52:08 xieyj
     * @history:
     */
    public List<Account> queryAccountList(Account data);
}
