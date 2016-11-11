/**
 * @Title IAJourBO.java 
 * @Package com.ibis.account.bo 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午3:19:46 
 * @version V1.0   
 */
package com.std.account.bo;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.AccountJour;
import com.std.account.enums.EBoolean;

/** 
 * @author: miyb 
 * @since: 2015-3-15 下午3:19:46 
 * @history:
 */
public interface IAJourBO extends IPaginableBO<AccountJour> {

    /**
     * 获取详情
     * @param order
     * @return 
     * @create: 2016年11月10日 下午6:10:50 xieyj
     * @history:
     */
    public AccountJour getAccountJour(String order);

    /**
     * 单独新增流水
     * @param accountNumber
     * @param preAmount
     * @param amount
     * @param bizType
     * @param refNo
     * @param remark 
     * @create: 2016年7月6日 下午8:32:54 xieyj
     * @history:
     */
    public void addJour(String accountNumber, Long preAmount, Long amount,
            String bizType, String refNo, String remark);

    /**
     * 调账结果录入
     * @param order
     * @param adjustUser
     * @param adjustResult 
     * @create: 2016年11月10日 下午6:12:00 xieyj
     * @history:
     */
    public void doAdjustAccount(String order, String adjustUser,
            EBoolean adjustResult);

    /**
     * 账户金额发生变动
     * @param order
     * @param status
     * @param payOrder 
     * @create: 2016年11月10日 下午6:14:10 xieyj
     * @history:
     */
    public void doTransAccount(String order, String status, String payOrder);

    /**
     * 对账结果录入
     * @param order
     * @param checkUser
     * @param checkResult
     * @return 
     * @create: 2016年1月15日 下午2:44:53 myb858
     * @history:
     */
    public void doCheckAccount(String order, String checkUser,
            EBoolean checkResult);

}
