/**
 * @Title YTOrderDAOImpl.java 
 * @Package com.ibis.account.dao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-5-9 下午2:50:38 
 * @version V1.0   
 */
package com.std.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.account.dao.IYTOrderDAO;
import com.std.account.dao.base.support.AMybatisTemplate;
import com.std.account.domain.YTOrder;

/** 
 * @author: miyb 
 * @since: 2015-5-9 下午2:50:38 
 * @history:
 */
@Repository("ytOrderDAOImpl")
public class YTOrderDAOImpl extends AMybatisTemplate implements IYTOrderDAO {

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(YTOrder data) {
        return super.insert(NAMESPACE.concat("insert_ytOrder"), data);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(YTOrder data) {
        return 0;
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public YTOrder select(YTOrder condition) {
        return super.select(NAMESPACE.concat("select_ytOrder"), condition,
            YTOrder.class);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public long selectTotalCount(YTOrder condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_ytOrder_count"),
            condition);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<YTOrder> selectList(YTOrder condition) {
        return super.selectList(NAMESPACE.concat("select_ytOrder"), condition,
            YTOrder.class);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<YTOrder> selectList(YTOrder condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_ytOrder"), start,
            count, condition, YTOrder.class);
    }

    /** 
     * @see com.xnjr.account.dao.ibis.account.dao.IYTOrderDAO#updateApproveOrder(com.ibis.account.domain.YTOrder)
     */
    @Override
    public int updateApproveOrder(YTOrder data) {
        return super.update(NAMESPACE.concat("update_approve_order"), data);
    }

}
