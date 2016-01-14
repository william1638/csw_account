/**
 * @Title IYTOrderDAO.java 
 * @Package com.ibis.account.dao 
 * @Description 
 * @author miyb  
 * @date 2015-5-9 下午2:50:23 
 * @version V1.0   
 */
package com.std.account.dao;

import com.std.account.dao.base.IBaseDAO;
import com.std.account.domain.YTOrder;

/** 
 * @author: miyb 
 * @since: 2015-5-9 下午2:50:23 
 * @history:
 */
public interface IYTOrderDAO extends IBaseDAO<YTOrder> {
    String NAMESPACE = IYTOrderDAO.class.getName().concat(".");

    /** 
     * 批准订单
     * @param data
     * @return 
     * @create: 2015-2-23 下午4:28:41 miyb
     * @history: 
     */
    public int updateApproveOrder(YTOrder data);
}
