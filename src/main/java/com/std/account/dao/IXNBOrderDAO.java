/**
 * @Title IXNBOrderDAO.java 
 * @Package com.ibis.account.dao 
 * @Description 
 * @author miyb  
 * @date 2015-2-14 下午2:10:36 
 * @version V1.0   
 */
package com.std.account.dao;

import com.std.account.dao.base.IBaseDAO;
import com.std.account.domain.XNBOrder;

/** 
 * @author: miyb 
 * @since: 2015-2-14 下午2:10:36 
 * @history:
 */
public interface IXNBOrderDAO extends IBaseDAO<XNBOrder> {
    String NAMESPACE = IXNBOrderDAO.class.getName().concat(".");

    /** 
     * 批准订单
     * @param data
     * @return 
     * @create: 2015-2-23 下午4:28:41 miyb
     * @history: 
     */
    public int updateApproveOrder(XNBOrder data);
}
