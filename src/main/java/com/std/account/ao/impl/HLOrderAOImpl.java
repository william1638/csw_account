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
import com.std.account.bo.ISmsOutBO;
import com.std.account.bo.IUserBO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.HLOrder;
import com.std.account.enums.EBizType;
import com.std.account.enums.EBoolean;
import com.std.account.enums.EDirection;
import com.std.account.enums.EHLOrderType;
import com.std.account.enums.EOrderStatus;
import com.std.account.exception.BizException;

/** 
 * @author: miyb 
 * @since: 2015-3-17 下午9:34:19 
 * @history:
 */
@Service
public class HLOrderAOImpl implements IHLOrderAO {
    @Autowired
    IUserBO userBO;

    @Autowired
    IAccountBO accountBO;

    @Autowired
    IHLOrderBO hlOrderBO;

    @Autowired
    ISmsOutBO smsOutBO;

    /** 
     * @see com.ibis.account.ao.IHLOrderAO#doBalance(java.lang.String, java.lang.Long, java.lang.String, java.lang.String)
     */
    @Override
    @Transactional
    public String doBalance(String accountNumber, Long amount,
            String applyUser, String applyNote) {
        accountBO.getAccount(accountNumber);
        return hlOrderBO.saveHLOrder(accountNumber, EHLOrderType.RG.getCode(),
            amount, applyUser, applyNote);
    }

    @Override
    public void doApprove(String hlNo, String approveUser,
            String approveResult, String approveNote) {
        HLOrder hlOrder = hlOrderBO.getHLOrder(hlNo);
        if (hlOrder == null) {
            throw new BizException("xn702514", "无对应充取订单");
        }
        if (!EOrderStatus.todoAPPROVE.getCode().equalsIgnoreCase(
            hlOrder.getStatus())) {
            throw new BizException("xn702514", "订单不处于待审批状态");
        }
        hlOrderBO.refreshApproveOrder(hlNo, approveUser, approveResult,
            approveNote);
        if (EDirection.PLUS.getCode().equalsIgnoreCase(// 蓝补
            hlOrder.getDirection())) {
            if (EBoolean.YES.getCode().equalsIgnoreCase(approveResult)) { // 资金变动
                accountBO.refreshAmount(hlOrder.getAccountNumber(),
                    hlOrder.getAmount(), EBizType.AJ_LB.getCode(),
                    hlOrder.getHlNo());

            }
        } else {
            if (EBoolean.YES.getCode().equalsIgnoreCase(approveResult)) { // 资金变动
                accountBO.refreshAmount(hlOrder.getAccountNumber(),
                    -hlOrder.getAmount(), EBizType.AJ_HC.getCode(),
                    hlOrder.getHlNo());

            }
        }

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
