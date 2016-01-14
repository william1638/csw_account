/**
 * @Title XNBOrderAOImpl.java 
 * @Package com.ibis.account.ao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-17 下午9:34:42 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.account.ao.IXNBOrderAO;
import com.std.account.bo.IAccountBO;
import com.std.account.bo.IXNBOrderBO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.XNBOrder;
import com.std.account.enums.EOrderStatus;

/** 
 * @author: miyb 
 * @since: 2015-3-17 下午9:34:42 
 * @history:
 */
@Service
public class XNBOrderAOImpl implements IXNBOrderAO {
    @Autowired
    IAccountBO accountBO;

    @Autowired
    IXNBOrderBO xnbOrderBO;

    /** 
     * @see com.ibis.account.ao.IXNBOrderAO#queryXNBOrderPage(int, int, com.ibis.account.domain.XNBOrder)
     */
    @Override
    public Paginable<XNBOrder> queryXNBOrderPage(int start, int limit,
            XNBOrder condition) {
        return xnbOrderBO.getPaginable(start, limit, condition);
    }

    /** 
     * @see com.ibis.account.ao.IXNBOrderAO#doExchange(boolean, java.lang.String, java.lang.String, java.lang.Long, java.lang.Long, java.lang.String)
     */
    @Override
    public String doExchange(boolean isNeedApprove, String accountNumber,
            String type, Long score, Long amount, String remark) {
        String status = EOrderStatus.UNAPPROVE.getCode();
        if (!isNeedApprove) {
            status = EOrderStatus.UNPAY.getCode();
        }
        return xnbOrderBO.saveXNBOrder(accountNumber, status, type, score,
            amount, remark);
    }
}
