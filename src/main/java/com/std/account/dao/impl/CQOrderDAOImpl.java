/**
 * @Title CQOrderDAOImpl.java 
 * @Package com.ibis.account.dao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-2-14 下午2:19:10 
 * @version V1.0   
 */
package com.std.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.account.dao.ICQOrderDAO;
import com.std.account.dao.base.support.AMybatisTemplate;
import com.std.account.domain.CQOrder;

/** 
 * @author: miyb 
 * @since: 2015-2-14 下午2:19:10 
 * @history:
 */
@Repository("cqOrderDAOImpl")
public class CQOrderDAOImpl extends AMybatisTemplate implements ICQOrderDAO {

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(CQOrder data) {
        return super.insert(NAMESPACE.concat("insert_cqOrder"), data);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(CQOrder data) {
        return 0;
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public CQOrder select(CQOrder condition) {
        return super.select(NAMESPACE.concat("select_cqOrder"), condition,
            CQOrder.class);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public long selectTotalCount(CQOrder condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_cqOrder_count"),
            condition);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<CQOrder> selectList(CQOrder condition) {
        return super.selectList(NAMESPACE.concat("select_cqOrder"), condition,
            CQOrder.class);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<CQOrder> selectList(CQOrder condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_cqOrder"), start,
            count, condition, CQOrder.class);
    }

    /** 
     * @see com.xnjr.account.dao.ibis.account.dao.ICQOrderDAO#updateApproveOrder(com.ibis.account.domain.CQOrder)
     */
    @Override
    public int updateApproveOrder(CQOrder data) {
        return super.update(NAMESPACE.concat("update_approve_order"), data);
    }

    /** 
     * @see com.xnjr.account.dao.ibis.account.dao.ICQOrderDAO#updatePayOrder(com.ibis.account.domain.CQOrder)
     */
    @Override
    public int updatePayOrder(CQOrder data) {
        return super.update(NAMESPACE.concat("update_pay_order"), data);
    }

    /** 
     * @see com.xnjr.account.dao.ibis.account.dao.ICQOrderDAO#updateCheckOrder(com.ibis.account.domain.CQOrder)
     */
    @Override
    public int updateCheckOrder(CQOrder data) {
        return super.update(NAMESPACE.concat("update_check_order"), data);
    }

    @Override
    public int insertChargeOrderYeepay(CQOrder data) {
        return super.insert(NAMESPACE.concat("insert_chargeOrderYeepay"), data);
    }

}
