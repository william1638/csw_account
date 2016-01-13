/**
 * @Title AFJourDAOImpl.java 
 * @Package com.ibis.account.dao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-2-14 下午2:18:43 
 * @version V1.0   
 */
package com.std.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.account.dao.IAFJourDAO;
import com.std.account.dao.base.support.AMybatisTemplate;
import com.std.account.domain.AccountFrozenJour;

/** 
 * @author: miyb 
 * @since: 2015-2-14 下午2:18:43 
 * @history:
 */
@Repository("afJourDAOImpl")
public class AFJourDAOImpl extends AMybatisTemplate implements IAFJourDAO {

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(AccountFrozenJour data) {
        return super.insert(NAMESPACE.concat("insert_afJour"), data);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(AccountFrozenJour data) {
        return 0;
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public AccountFrozenJour select(AccountFrozenJour condition) {
        return super.select(NAMESPACE.concat("select_afJour"), condition,
            AccountFrozenJour.class);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public long selectTotalCount(AccountFrozenJour condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_afJour_count"),
            condition);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<AccountFrozenJour> selectList(AccountFrozenJour condition) {
        return super.selectList(NAMESPACE.concat("select_afJour"), condition,
            AccountFrozenJour.class);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<AccountFrozenJour> selectList(AccountFrozenJour condition,
            int start, int count) {
        return super.selectList(NAMESPACE.concat("select_afJour"), start,
            count, condition, AccountFrozenJour.class);
    }

}
