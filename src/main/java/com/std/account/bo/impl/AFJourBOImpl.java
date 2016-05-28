/**
 * @Title AFJourBOImpl.java 
 * @Package com.ibis.account.bo.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午3:21:54 
 * @version V1.0   
 */
package com.std.account.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.IAFJourBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.dao.IAFJourDAO;
import com.std.account.domain.AccountFrozenJour;

/** 
 * @author: miyb 
 * @since: 2015-3-15 下午3:21:54 
 * @history:
 */
@Component
public class AFJourBOImpl extends PaginableBOImpl<AccountFrozenJour> implements
        IAFJourBO {
    @Autowired
    private IAFJourDAO afJourDAO;

}
