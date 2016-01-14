/**
 * @Title ZZOrderDAOImpl.java 
 * @Package com.ibis.account.dao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-2-14 下午2:19:48 
 * @version V1.0   
 */
package com.std.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.account.dao.IZZOrderDAO;
import com.std.account.dao.base.support.AMybatisTemplate;
import com.std.account.domain.ZZOrder;

/** 
 * @author: miyb 
 * @since: 2015-2-14 下午2:19:48 
 * @history:
 */
@Repository("zzOrderDAOImpl")
public class ZZOrderDAOImpl extends AMybatisTemplate implements IZZOrderDAO {

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(ZZOrder data) {
        return super.insert(NAMESPACE.concat("insert_zzOrder"), data);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(ZZOrder data) {
        return 0;
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public ZZOrder select(ZZOrder condition) {
        return super.select(NAMESPACE.concat("select_zzOrder"), condition,
            ZZOrder.class);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public long selectTotalCount(ZZOrder condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_zzOrder_count"),
            condition);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<ZZOrder> selectList(ZZOrder condition) {
        return super.selectList(NAMESPACE.concat("select_zzOrder"), condition,
            ZZOrder.class);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<ZZOrder> selectList(ZZOrder condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_zzOrder"), start,
            count, condition, ZZOrder.class);
    }

    @Override
    public List<ZZOrder> doStatisticsDvalue(ZZOrder condition) {
        return super.selectList(NAMESPACE.concat("select_doStatisticsDvalue"),
            condition, ZZOrder.class);
    }

}
