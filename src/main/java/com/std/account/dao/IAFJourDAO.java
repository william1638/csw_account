/**
 * @Title IAFJourDAO.java 
 * @Package com.ibis.account.dao 
 * @Description 
 * @author miyb  
 * @date 2015-2-14 下午2:08:03 
 * @version V1.0   
 */
package com.std.account.dao;

import com.std.account.dao.base.IBaseDAO;
import com.std.account.domain.AccountFrozenJour;

/** 
 * @author: miyb 
 * @since: 2015-2-14 下午2:08:03 
 * @history:
 */
public interface IAFJourDAO extends IBaseDAO<AccountFrozenJour> {
    String NAMESPACE = IAFJourDAO.class.getName().concat(".");
}
