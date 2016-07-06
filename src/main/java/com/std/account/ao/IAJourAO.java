/**
 * @Title IAJourAO.java 
 * @Package com.ibis.account.ao 
 * @Description 
 * @author miyb  
 * @date 2015-5-7 上午9:29:01 
 * @version V1.0   
 */
package com.std.account.ao;

import com.std.account.annotation.ServiceModule;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.AccountJour;

/** 
 * @author: miyb 
 * @since: 2015-5-7 上午9:29:01 
 * @history:
 */
@ServiceModule
public interface IAJourAO {
    String DEFAULT_ORDER_COLUMN = "aj_no";

    /**
     * 分页获取
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2015-5-7 上午10:53:38 miyb
     * @history:
     */
    Paginable<AccountJour> queryAccountJourPage(int start, int limit,
            AccountJour condition);

    /**
     * 对账结果录入：告诉系统哪些交易流水已对账待调帐,此时红冲蓝补的订单已经生成
     * @param ajNo
     * @param checkUser
     * @param amount 
     * @create: 2016年1月18日 下午1:37:49 myb858
     * @history:
     */
    void doCheckJour(Long ajNo, String checkUser, Long amount);

    /**
     * 对账审批:免对账
     * @param hlNo
     * @param approveUser
     * @param approveResult
     * @param approveNote 
     * @create: 2016年1月18日 下午1:50:54 myb858
     * @history:
     */
    void doApproveCheckJour(String hlNo, String approveUser,
            String approveResult, String approveNote);
}
