/**
 * @Title BankDAOImpl.java 
 * @Package com.ibis.account.dao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-6-16 上午10:43:51 
 * @version V1.0   
 */
package com.std.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.account.dao.IBankDAO;
import com.std.account.dao.base.support.AMybatisTemplate;
import com.std.account.domain.Bank;

/** 
 * @author: miyb 
 * @since: 2015-6-16 上午10:43:51 
 * @history:
 */
@Repository("bankDAOImpl")
public class BankDAOImpl extends AMybatisTemplate implements IBankDAO {

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(Bank data) {
        return super.insert(NAMESPACE.concat("insert_bank"), data);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(Bank data) {
        return super.delete(NAMESPACE.concat("delete_bank"), data);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public Bank select(Bank condition) {
        return (Bank) super.select(NAMESPACE.concat("select_bank"), condition,
            Bank.class);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public long selectTotalCount(Bank condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_bank_count"),
            condition);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<Bank> selectList(Bank condition) {
        return super.selectList(NAMESPACE.concat("select_bank"), condition,
            Bank.class);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<Bank> selectList(Bank condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_bank"), start, count,
            condition, Bank.class);
    }

    /** 
     * @see com.xnjr.account.dao.ibis.account.dao.IBankDAO#update(com.ibis.account.domain.Bank)
     */
    @Override
    public int update(Bank data) {
        return super.update(NAMESPACE.concat("update_bank"), data);
    }

}
