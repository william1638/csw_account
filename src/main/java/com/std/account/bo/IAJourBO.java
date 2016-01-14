/**
 * @Title IAJourBO.java 
 * @Package com.ibis.account.bo 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午3:19:46 
 * @version V1.0   
 */
package com.std.account.bo;

import java.util.List;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.AccountJour;

/** 
 * @author: miyb 
 * @since: 2015-3-15 下午3:19:46 
 * @history:
 */
public interface IAJourBO extends IPaginableBO<AccountJour> {

    public int refreshCheckJour(Long aJourNo, String checkUser,
            String checkResult, String remark);

    /**
     * 获取单条账户流水记录
     * @param ajNo
     * @return 
     * @create: 2015-5-5 上午11:21:13 miyb
     * @history:
     */
    public AccountJour getAccountJour(Long ajNo);

    /**
     * 获取账户流水列表
     * @param condition
     * @return 
     * @create: 2015-5-5 上午11:21:44 miyb
     * @history:
     */
    public List<AccountJour> queryAccountJourList(AccountJour condition);
}
