/**
 * @Title BankAOImpl.java 
 * @Package com.ibis.account.ao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-6-16 下午4:35:34 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.account.ao.IBankAO;
import com.std.account.bo.IBankBO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.Bank;
import com.std.account.exception.BizException;

/** 
 * @author: miyb 
 * @since: 2015-6-16 下午4:35:34 
 * @history:
 */
@Service
public class BankAOImpl implements IBankAO {
    @Autowired
    IBankBO bankBO;

    /** 
     * @see com.ibis.account.ao.IBankAO#addBank(com.ibis.account.domain.Bank)
     */
    @Override
    public String addBank(Bank data) {
        if (data != null && !bankBO.isBankExist(data.getId())) {
            bankBO.saveBank(data);
        } else {
            throw new BizException("li01006", "银行编号已经存在！");
        }
        return data.getBankNo();
    }

    @Override
    public boolean dropBank(Long id) {
        if (!bankBO.isBankExist((id))) {
            throw new BizException("xn702000", "删除银行不存在！");
        }
        bankBO.removeBank(id);
        return true;
    }

    /** 
     * @see com.ibis.account.ao.IBankAO#editBank(com.ibis.account.domain.Bank)
     */
    @Override
    public boolean editBank(Bank data) {
        if (data != null && bankBO.isBankExist(data.getId())) {
            bankBO.refreshBank(data);
        } else {
            throw new BizException("li01007", "银行编号不存在！");
        }
        return true;
    }

    /** 
     * @see com.ibis.account.ao.IBankAO#queryBankPage(int, int, com.ibis.account.domain.Bank)
     */
    @Override
    public Paginable<Bank> queryBankPage(int start, int limit, Bank condition) {
        return bankBO.getPaginable(start, limit, condition);
    }

    /** 
     * @see com.ibis.account.ao.IBankAO#queryBankList(com.ibis.account.domain.Bank)
     */
    @Override
    public List<Bank> queryBankList(Bank condition) {
        return bankBO.queryBankList(condition);
    }

}
