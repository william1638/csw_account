/**
 * @Title AFJourAOImpl.java 
 * @Package com.ibis.account.ao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-5-7 下午4:53:36 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.account.ao.IAFJourAO;
import com.std.account.bo.IAFJourBO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.AccountFrozenJour;

/** 
 * @author: miyb 
 * @since: 2015-5-7 下午4:53:36 
 * @history:
 */
@Service
public class AFJourAOImpl implements IAFJourAO {
    @Autowired
    IAFJourBO afJourBO;

    /** 
     * @see com.ibis.account.ao.IAFJourAO#queryAccountFrozenJourPage(int, int, com.ibis.account.domain.AccountFrozenJour)
     */
    @Override
    public Paginable<AccountFrozenJour> queryAccountFrozenJourPage(int start,
            int limit, AccountFrozenJour condition) {
        return afJourBO.getPaginable(start, limit, condition);
    }

}
