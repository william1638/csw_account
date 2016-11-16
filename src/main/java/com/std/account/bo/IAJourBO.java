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
import com.std.account.enums.EBizType;
import com.std.account.enums.EBoolean;
import com.std.account.enums.EChannelType;
import com.std.account.enums.EPayType;

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
     * 新增未变动金额之流水
     * @param systemCode
     * @param accountName
     * @param accountNumber
     * @param channelType
     * @param payType
     * @param bizType
     * @param preAmount
     * @param transAmount
     * @return 
     * @create: 2016年11月16日 下午8:53:18 myb858
     * @history:
     */
    public String addTochangeJour(String systemCode, String accountName,
            String accountNumber, EChannelType channelType, EPayType payType,
            EBizType bizType, Long preAmount, Long transAmount);

    /**
     * 新增已变动金额之流水
     * @param systemCode
     * @param accountName
     * @param accountNumber
     * @param bizType
     * @param bizNote
     * @param preAmount
     * @param amount
     * @param remark
     * @return 
     * @create: 2016年11月16日 下午5:39:58 myb858
     * @history:
     */
    public String addChangedJour(String systemCode, String accountName,
            String accountNumber, EChannelType channelType, EPayType payType,
            String bizType, String bizNote, Long preAmount, Long amount);

    /**
     * 账户金额发生变动
     * @param order
     * @param payOrder 
     * @create: 2016年11月10日 下午6:14:10 xieyj
     * @history:
     */
    public void doTransAccount(String order, String payOrder);

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

    /**
     * 调账结果录入
     * @param order
     * @param adjustUser 
     * @create: 2016年11月11日 上午10:26:32 xieyj
     * @history:
     */
    public void doAdjustAccount(String order, String adjustUser);

}
