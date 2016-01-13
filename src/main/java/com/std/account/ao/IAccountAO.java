/**
 * @Title IAccountAO.java 
 * @Package com.ibis.account.ao 
 * @Description 
 * @author miyb  
 * @date 2015-3-17 下午6:21:02 
 * @version V1.0   
 */
package com.std.account.ao;

import java.util.List;

import com.std.account.annotation.ServiceModule;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.Account;

/** 
 * @author: miyb 
 * @since: 2015-3-17 下午6:21:02 
 * @history:
 */
@ServiceModule
public interface IAccountAO {
    String DEFAULT_ORDER_COLUMN = "account_number";

    /** 
     * 根据userId查询账户
     * @param userId
     * @return 
     * @create: 2015-3-17 下午6:28:56 miyb
     * @history: 
     */
    public Account getAccount(String userId);

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
     * 账户列表查询
     * @param condition
     * @return 
     * @create: 2015年12月12日 下午5:06:40 haiqingzheng
     * @history:
     */
    public List<Account> queryAccountList(Account condition);

    /** 
     * 判断是否存在账户
     * @param userId
     * @return 
     * @create: 2015-5-6 上午10:29:40 miyb
     * @history: 
     */
    public String isExistAccount(String userId);

    /** 
     * 申领账户
     * @param userId
     * @param currency
     * @return 
     * @create: 2015-3-17 下午6:28:49 miyb
     * @history: 
     */
    public String distributeAccount(String userId, String currency);

    /**
     * 应用申请冻结/解冻资金
     * @param userId
     * @param amount 正数解冻；负数冻结
     * @param oppositeSystem
     * @param oppositeAccount
     * @param remark
     * @return 
     * @create: 2015年11月18日 下午1:28:05 myb858
     * @history:
     */
    public void doFreeze(String userId, Long amount, String oppositeSystem,
            String oppositeAccount, String remark);
}
