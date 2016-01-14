/**
 * @Title AJourAOImpl.java 
 * @Package com.ibis.account.ao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-5-7 上午11:32:16 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.account.ao.IAJourAO;
import com.std.account.bo.IAJourBO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.AccountJour;

/** 
 * @author: miyb 
 * @since: 2015-5-7 上午11:32:16 
 * @history:
 */
@Service
public class AJourAOImpl implements IAJourAO {
    @Autowired
    IAJourBO aJourBO;

    /** 
     * @see com.ibis.account.ao.IAJourAO#queryAccountJourPage(int, int, com.ibis.account.domain.AccountJour)
     */
    @Override
    public Paginable<AccountJour> queryAccountJourPage(int start, int limit,
            AccountJour condition) {
        return aJourBO.getPaginable(start, limit, condition);
    }

}
