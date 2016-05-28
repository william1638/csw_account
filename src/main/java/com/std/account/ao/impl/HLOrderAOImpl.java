/**
 * @Title HLOrderAOImpl.java 
 * @Package com.ibis.account.ao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-17 下午9:34:19 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.account.ao.IHLOrderAO;
import com.std.account.bo.IAccountBO;
import com.std.account.bo.IHLOrderBO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.HLOrder;
import com.std.account.enums.EBizType;
import com.std.account.enums.EBoolean;
import com.std.account.enums.EDirection;
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
    IAccountBO accountBO;

    @Autowired
    IHLOrderBO hlOrderBO;

    @Override
    public Paginable<HLOrder> queryHLOrderPage(int start, int limit,
            HLOrder condition) {
        return hlOrderBO.getPaginable(start, limit, condition);
    }

    @Override
    @Transactional
    public String doBalance(String accountNumber, String direction,
            Long amount, String applyUser, String applyNote) {
        accountBO.getAccount(accountNumber);
        if (EDirection.PLUS.getCode().equalsIgnoreCase(direction)) {
            return hlOrderBO.saveHLOrder(accountNumber, EDirection.PLUS,
                amount, applyUser, applyNote);
        } else {
            return hlOrderBO.saveHLOrder(accountNumber, EDirection.MINUS,
                amount, applyUser, applyNote);
        }

    }

    @Override
    @Transactional
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
        hlOrderBO.refreshApproveOrder(hlNo, approveUser, EBoolean
            .getBooleanResultMap().get(approveResult), approveNote);
        if (EDirection.PLUS.getCode().equalsIgnoreCase(// 蓝补
            hlOrder.getDirection())) {
            if (EBoolean.YES.getCode().equalsIgnoreCase(approveResult)) { // 资金变动
                accountBO.refreshAmount(hlOrder.getAccountNumber(),
                    hlOrder.getAmount(), hlOrder.getCode(), EBizType.AJ_LB);
            }
        } else {
            if (EBoolean.YES.getCode().equalsIgnoreCase(approveResult)) { // 资金变动
                accountBO.refreshAmount(hlOrder.getAccountNumber(),
                    -hlOrder.getAmount(), hlOrder.getCode(), EBizType.AJ_HC);
            }
        }

    }
}
