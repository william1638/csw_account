/**
 * @Title IAFJourAO.java 
 * @Package com.ibis.account.ao 
 * @Description 
 * @author miyb  
 * @date 2015-5-7 上午9:28:36 
 * @version V1.0   
 */
package com.std.account.ao;

import com.std.account.annotation.ServiceModule;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.AccountFrozenJour;

/** 
 * @author: miyb 
 * @since: 2015-5-7 上午9:28:36 
 * @history:
 */
@ServiceModule
public interface IAFJourAO {
    String DEFAULT_ORDER_COLUMN = "afj_no";

    /** 
     * 分页获取
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2015-5-7 下午4:52:33 miyb
     * @history: 
     */
    Paginable<AccountFrozenJour> queryAccountFrozenJourPage(int start,
            int limit, AccountFrozenJour condition);

}
