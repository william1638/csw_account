/**
 * @Title IHLOrderDAO.java 
 * @Package com.ibis.account.dao 
 * @Description 
 * @author miyb  
 * @date 2015-2-14 下午2:09:26 
 * @version V1.0   
 */
package com.std.account.dao;

import com.std.account.dao.base.IBaseDAO;
import com.std.account.domain.HLOrder;

/** 
 * @author: miyb 
 * @since: 2015-2-14 下午2:09:26 
 * @history:
 */
public interface IHLOrderDAO extends IBaseDAO<HLOrder> {
    String NAMESPACE = IHLOrderDAO.class.getName().concat(".");

    /** 
     * 批准订单
     * @param data
     * @return 
     * @create: 2015-2-23 下午4:28:41 miyb
     * @history: 
     */
    public int updateApproveOrder(HLOrder data);
}
