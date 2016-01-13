/**
 * @Title IBankDAO.java 
 * @Package com.ibis.account.dao 
 * @Description 
 * @author miyb  
 * @date 2015-6-16 上午10:42:25 
 * @version V1.0   
 */
package com.std.account.dao;

import com.std.account.dao.base.IBaseDAO;
import com.std.account.domain.Bank;

/** 
 * @author: miyb 
 * @since: 2015-6-16 上午10:42:25 
 * @history:
 */
public interface IBankDAO extends IBaseDAO<Bank> {
    String NAMESPACE = IBankDAO.class.getName().concat(".");

    /** 
     * @param data
     * @return 
     * @create: 2015-6-16 下午3:17:36 miyb
     * @history: 
     */
    int update(Bank data);
}
