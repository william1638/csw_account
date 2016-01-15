/**
 * @Title XNBOrderDAOImpl.java 
 * @Package com.std.account.dao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-2-14 下午2:19:36 
 * @version V1.0   
 */
package com.std.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.account.dao.IXNBOrderDAO;
import com.std.account.dao.base.support.AMybatisTemplate;
import com.std.account.domain.XNBOrder;

/** 
 * @author: miyb 
 * @since: 2015-2-14 下午2:19:36 
 * @history:
 */
@Repository("xnbOrderDAOImpl")
public class XNBOrderDAOImpl extends AMybatisTemplate implements IXNBOrderDAO {

    /** 
     * @see com.std.account.dao.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(XNBOrder data) {
        return super.insert(NAMESPACE.concat("insert_xnbOrder"), data);
    }

    /** 
     * @see com.std.account.dao.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(XNBOrder data) {
        return 0;
    }

    /** 
     * @see com.std.account.dao.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public XNBOrder select(XNBOrder condition) {
        return super.select(NAMESPACE.concat("select_xnbOrder"), condition,
            XNBOrder.class);
    }

    /** 
     * @see com.std.account.dao.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public long selectTotalCount(XNBOrder condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_xnbOrder_count"),
            condition);
    }

    /** 
     * @see com.std.account.dao.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<XNBOrder> selectList(XNBOrder condition) {
        return super.selectList(NAMESPACE.concat("select_xnbOrder"), condition,
            XNBOrder.class);
    }

    /** 
     * @see com.std.account.dao.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<XNBOrder> selectList(XNBOrder condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_xnbOrder"), start,
            count, condition, XNBOrder.class);
    }

    /** 
     * @see com.xnjr.account.dao.std.account.dao.IXNBOrderDAO#updateApproveOrder(com.std.account.domain.XNBOrder)
     */
    @Override
    public int updateApproveOrder(XNBOrder data) {
        return super.update(NAMESPACE.concat("update_approve_order"), data);
    }

}
