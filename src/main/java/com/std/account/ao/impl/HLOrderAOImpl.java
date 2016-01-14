/**
 * @Title HLOrderAOImpl.java 
 * @Package com.ibis.account.ao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-17 下午9:34:19 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.account.ao.IHLOrderAO;
import com.std.account.bo.IAccountBO;
import com.std.account.bo.IHLOrderBO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.HLOrder;

/** 
 * @author: miyb 
 * @since: 2015-3-17 下午9:34:19 
 * @history:
 */
@Service
public class HLOrderAOImpl implements IHLOrderAO {
    @Autowired
    IAccountBO accountBO;

    @Autowired
    IHLOrderBO hlOrderBO;

    /** 
     * @see com.ibis.account.ao.IHLOrderAO#doBalance(java.lang.String, java.lang.Long, java.lang.String, java.lang.String)
     */
    @Override
    @Transactional
    public String doBalance(String accountNumber, Long amount,
            String applyUser, String applyNote) {
        accountBO.getAccount(accountNumber);
        return hlOrderBO.saveHLOrder(accountNumber, amount, applyUser,
            applyNote);
    }

    /** 
     * @see com.ibis.account.ao.IHLOrderAO#queryHLOrderPage(int, int, com.ibis.account.domain.HLOrder)
     */
    @Override
    public Paginable<HLOrder> queryHLOrderPage(int start, int limit,
            HLOrder condition) {
        return hlOrderBO.getPaginable(start, limit, condition);
    }

    /**
     * @see com.std.account.ao.IHLOrderAO#queryHLOrderPage(com.std.account.domain.HLOrder)
     */
    @Override
    public List<HLOrder> queryHLOrderList(HLOrder condition) {
        return hlOrderBO.queryHLOrderList(condition);
    }

}
