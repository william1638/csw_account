/**
 * @Title IAFJourBO.java 
 * @Package com.ibis.account.bo 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午3:18:03 
 * @version V1.0   
 */
package com.std.account.bo;

import java.util.List;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.AccountFrozenJour;

/** 
 * @author: miyb 
 * @since: 2015-3-15 下午3:18:03 
 * @history:
 */
public interface IAFJourBO extends IPaginableBO<AccountFrozenJour> {
    /**
     * 获取单条冻结记录
     * @param afjNo
     * @return 
     * @create: 2015-5-5 上午11:16:43 miyb
     * @history:
     */
    public AccountFrozenJour getAFJour(Long afjNo);

    /**
     * 获取冻结记录列表
     * @param condition
     * @return 
     * @create: 2015-5-5 上午11:17:32 miyb
     * @history:
     */
    public List<AccountFrozenJour> queryAFJourList(AccountFrozenJour condition);
}
