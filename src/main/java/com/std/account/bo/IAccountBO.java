/**
 * @Title IAccountBO.java 
 * @Package com.ibis.account.bo 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午3:15:49 
 * @version V1.0   
 */
package com.std.account.bo;

import java.util.List;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.Account;
import com.std.account.enums.EAccountStatus;
import com.std.account.enums.EBizType;
import com.std.account.enums.ECurrency;

/** 
 * @author: miyb 
 * @since: 2015-3-15 下午3:15:49 
 * @history:
 */
public interface IAccountBO extends IPaginableBO<Account> {
    /**
     * 分配账户
     * @param userId
     * @param currency
     * @return 
     * @create: 2015-5-4 下午5:34:16 miyb
     * @history:
     */
    public String distributeAccount(String userId, String realName,
            ECurrency currency);

    /**
     * 更新账户余额（形成需要对账的流水记录）
     * @param accountNumber 账号
     * @param transAmount 账户余额的发生金额(有正负之分)
     * @param refNo 关联订单号
     * @param bizType 业务类型
     * @return
     * @create: 2015-5-4 下午5:34:37 miyb
     * @history:
     */
    public void refreshAmount(String accountNumber, Long transAmount,
            String refNo, EBizType bizType);

    /**
     * 更新账户余额（形成不需要对账的流水记录）
     * @param accountNumber
     * @param transAmount
     * @param bizType
     * @param refNo
     * @param remark
     * @return 
     * @create: 2016年5月27日 下午12:47:24 myb858
     * @history:
     */
    public void refreshAmountWithoutCheck(String accountNumber,
            Long transAmount, String refNo, EBizType bizType);

    /**
     * 冻结金额（余额减少frezenAmount，冻结金额加上frezenAmount，同时记录2流水）
     * @param accountNumber 账号
     * @param freezeAmount 冻结金额（正数）
     * @param bizType 业务类型
     * @param refNo 关联订单号
     * @return 
     * @create: 2015-5-9 下午3:46:59 miyb
     * @history:
     */
    public void freezeAmount(String accountNumber, Long freezeAmount,
            String refNo, EBizType bizType);

    /**
     * 解冻的金额不返到余额(只记录冻结流水）
     * @param accountNumber 账号
     * @param unfreezeAmount 冻结金额（正数）
     * @param refNo 关联订单号
     * @param bizType 业务类型
     * @return 
     * @create: 2015-5-9 下午3:46:59 miyb
     * @history:
     */
    public void unfreezeAmount(String accountNumber, Long unfreezeAmount,
            String refNo, EBizType bizType);

    /**
     * 解冻的金额返到余额（不仅记录冻结流水，还记录资金流水）
     * @param accountNumber 账号
     * @param unfreezeAmount 冻结金额（正数）
     * @param refNo 关联订单号
     * @param bizType 业务类型
     * @return 
     * @create: 2015-5-9 下午3:46:59 miyb
     * @history:
     */
    public void unfreezeAmountToAmount(String accountNumber,
            Long unfreezeAmount, String refNo, EBizType bizType);

    public void refreshStatus(String accountNumber, EAccountStatus status);

    public void refreshRealName(String userId, String realName);

    /**
     * 获取账户列表
     * @param data
     * @return 
     * @create: 2015-5-4 下午5:33:47 miyb
     * @history:
     */
    public List<Account> queryAccountList(Account data);

    /**
     * 获取账户
     * @param accountNumber
     * @return 
     * @create: 2015-5-4 下午5:33:28 miyb
     * @history:
     */
    public Account getAccount(String accountNumber);

    /** 
     * 获取账户
     * @param userId
     * @return 
     * @create: 2015-5-6 上午11:18:46 miyb
     * @history: 
     */
    public Account getAccountByUserId(String userId);

}
