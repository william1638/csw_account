/**
 * @Title BankBOImpl.java 
 * @Package com.ibis.account.bo.impl 
 * @Description 
 * @author miyb  
 * @date 2015-6-16 下午4:52:49 
 * @version V1.0   
 */
package com.std.account.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.IBankBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.dao.IBankDAO;
import com.std.account.domain.Bank;

/** 
 * @author: miyb 
 * @since: 2015-6-16 下午4:52:49 
 * @history:
 */
@Component
public class BankBOImpl extends PaginableBOImpl<Bank> implements IBankBO {
    @Autowired
    private IBankDAO bankDAO;

    /** 
     * @see com.ibis.account.bo.IBankBO#saveBank(com.ibis.account.domain.Bank)
     */
    @Override
    public int saveBank(Bank data) {
        int count = 0;
        if (data != null) {
            count = bankDAO.insert(data);
        }
        return count;
    }

    @Override
    public int removeBank(Long id) {
        int count = 0;
        if (id > 0) {
            Bank data = new Bank();
            data.setId(id);
            count = bankDAO.delete(data);
        }
        return count;
    }

    /** 
     * @see com.ibis.account.bo.IBankBO#refreshBank(com.ibis.account.domain.Bank)
     */
    @Override
    public int refreshBank(Bank data) {
        return bankDAO.update(data);
    }

    @Override
    public Bank getBank(Long id) {
        Bank data = null;
        if (id > 0) {
            Bank condition = new Bank();
            condition.setId(id);
            data = bankDAO.select(condition);
        }
        return data;
    }

    /** 
     * @see com.ibis.account.bo.IBankBO#queryBankList(com.ibis.account.domain.Bank)
     */
    @Override
    public List<Bank> queryBankList(Bank data) {
        return bankDAO.selectList(data);
    }

    @Override
    public boolean isBankExist(Long id) {
        Bank bank = new Bank();
        bank.setId(id);
        if (bankDAO.selectTotalCount(bank) == 1) {
            return true;
        }
        return false;
    }

}
