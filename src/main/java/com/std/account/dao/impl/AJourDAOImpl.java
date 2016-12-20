/**
 * @Title AJourDAOImpl.java 
 * @Package com.ibis.account.dao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-2-14 下午2:18:55 
 * @version V1.0   
 */
package com.std.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.account.dao.IAJourDAO;
import com.std.account.dao.base.support.AMybatisTemplate;
import com.std.account.domain.Jour;

/** 
 * @author: miyb 
 * @since: 2015-2-14 下午2:18:55 
 * @history:
 */
@Repository("aJourDAOImpl")
public class AJourDAOImpl extends AMybatisTemplate implements IAJourDAO {

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(Jour data) {
        return super.insert(NAMESPACE.concat("insert_accountJour"), data);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(Jour data) {
        return 0;
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public Jour select(Jour condition) {
        return super.select(NAMESPACE.concat("select_accountJour"), condition,
            Jour.class);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public long selectTotalCount(Jour condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_accountJour_count"), condition);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<Jour> selectList(Jour condition) {
        return super.selectList(NAMESPACE.concat("select_accountJour"),
            condition, Jour.class);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<Jour> selectList(Jour condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_accountJour"), start,
            count, condition, Jour.class);
    }

    /** 
     * @see com.std.account.dao.IAJourDAO#updateTrans(com.std.account.domain.Jour)
     */
    @Override
    public int updateTrans(Jour data) {
        return super.update(NAMESPACE.concat("update_trans_account"), data);
    }

    /** 
     * @see com.xnjr.account.dao.ibis.account.dao.IAJourDAO#updateCheck(com.Jour.account.domain.AccountJour)
     */
    @Override
    public int updateCheck(Jour data) {
        return super.update(NAMESPACE.concat("update_check_account"), data);
    }

    /** 
     * @see com.std.account.dao.IAJourDAO#updateAdjust(com.std.account.domain.Jour)
     */
    @Override
    public int updateAdjust(Jour data) {
        return super.update(NAMESPACE.concat("update_adjust_account"), data);
    }
}
