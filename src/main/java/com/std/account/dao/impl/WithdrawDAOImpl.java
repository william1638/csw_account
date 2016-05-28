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

import com.std.account.dao.IWithdrawDAO;
import com.std.account.dao.base.support.AMybatisTemplate;
import com.std.account.domain.Withdraw;

/** 
 * @author: miyb 
 * @since: 2015-2-14 下午2:19:10 
 * @history:
 */
@Repository("withdrawDAOImpl")
public class WithdrawDAOImpl extends AMybatisTemplate implements IWithdrawDAO {

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(Withdraw data) {
        return super.insert(NAMESPACE.concat("insert_withdraw"), data);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(Withdraw data) {
        return 0;
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public Withdraw select(Withdraw condition) {
        return super.select(NAMESPACE.concat("select_withdraw"), condition,
            Withdraw.class);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public long selectTotalCount(Withdraw condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_withdraw_count"), condition);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<Withdraw> selectList(Withdraw condition) {
        return super.selectList(NAMESPACE.concat("select_withdraw"), condition,
            Withdraw.class);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<Withdraw> selectList(Withdraw condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_withdraw"), start,
            count, condition, Withdraw.class);
    }

    /** 
     * @see com.xnjr.account.dao.IWithdrawDAO.account.dao.ICQOrderDAO#updateApproveOrder(com.Withdraw.account.domain.CQOrder)
     */
    @Override
    public int updateApproveOrder(Withdraw data) {
        return super.update(NAMESPACE.concat("update_approve_order"), data);
    }

    /** 
     * @see com.xnjr.account.dao.IWithdrawDAO.account.dao.ICQOrderDAO#updatePayOrder(com.Withdraw.account.domain.CQOrder)
     */
    @Override
    public int updatePayOrder(Withdraw data) {
        return super.update(NAMESPACE.concat("update_pay_order"), data);
    }

}
