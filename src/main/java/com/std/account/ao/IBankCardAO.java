/**
 * @Title IUserBankCardAO.java 
 * @Package com.ibis.pz.user 
 * @Description 
 * @author miyb  
 * @date 2015-3-8 上午10:48:42 
 * @version V1.0   
 */
package com.std.account.ao;

import com.std.account.bo.base.Paginable;
import com.std.account.domain.BankCard;

/** 
 * @author: miyb 
 * @since: 2015-3-8 上午10:48:42 
 * @history:
 */
public interface IBankCardAO {
    String DEFAULT_ORDER_COLUMN = "user_id";

    /**
     * 根据userId获取银行卡
     * @param userId
     * @return 
     * @create: 2015-5-16 下午8:49:28 miyb
     * @history:
     */
    public BankCard getBankCard(String userId);

    /**
     * 
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2015年10月30日 上午9:54:37 myb858
     * @history:
     */
    public Paginable<BankCard> queryBankCardPage(int start, int limit,
            BankCard condition);

    /**
     * 绑定银行卡
     * @param userId
     * @param bankCode
     * @param bankName
     * @param bankCardNo
     * @param subbranch
     * @param bindMobile
     * @return 
     * @create: 2015年12月6日 下午12:13:07 myb858
     * @history:
     */
    public void doBindBandCard(String userId, String bankCode, String bankName,
            String bankCardNo, String subbranch, String bindMobile);

}
