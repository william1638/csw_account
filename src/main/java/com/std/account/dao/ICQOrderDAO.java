/**
 * @Title ICQOrderDAO.java 
 * @Package com.ibis.account.dao 
 * @Description 
 * @author miyb  
 * @date 2015-2-14 下午2:09:12 
 * @version V1.0   
 */
package com.std.account.dao;

import com.std.account.dao.base.IBaseDAO;
import com.std.account.domain.CQOrder;

/** 
 * @author: miyb 
 * @since: 2015-2-14 下午2:09:12 
 * @history:
 */
public interface ICQOrderDAO extends IBaseDAO<CQOrder> {
    String NAMESPACE = ICQOrderDAO.class.getName().concat(".");

    /** 
     * 批准订单
     * @param data
     * @return 
     * @create: 2015-2-23 下午4:28:41 miyb
     * @history: 
     */
    public int updateApproveOrder(CQOrder data);

    /** 
     * 支付订单
     * @param data
     * @return 
     * @create: 2015-2-23 下午4:28:41 miyb
     * @history: 
     */
    public int updatePayOrder(CQOrder data);

    /** 
     * 三方自动对账
     * @param data
     * @return 
     * @create: 2015-5-4 下午4:23:13 miyb
     * @history: 
     */
    public int updateCheckOrder(CQOrder data);

}
