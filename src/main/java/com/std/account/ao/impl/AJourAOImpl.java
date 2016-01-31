/**
 * @Title AJourAOImpl.java 
 * @Package com.ibis.account.ao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-5-7 上午11:32:16 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.account.ao.IAJourAO;
import com.std.account.bo.IAJourBO;
import com.std.account.bo.IAccountBO;
import com.std.account.bo.IHLOrderBO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.AccountJour;
import com.std.account.domain.HLOrder;
import com.std.account.enums.EAccountJourStatus;
import com.std.account.enums.EBizType;
import com.std.account.enums.EBoolean;
import com.std.account.enums.EDirection;
import com.std.account.enums.EHLOrderType;
import com.std.account.enums.EOrderStatus;
import com.std.account.exception.BizException;

/** 
 * @author: miyb 
 * @since: 2015-5-7 上午11:32:16 
 * @history:
 */
@Service
public class AJourAOImpl implements IAJourAO {
    @Autowired
    IAJourBO aJourBO;

    @Autowired
    IHLOrderBO hlOrderBO;

    @Autowired
    IAccountBO accountBO;

    /** 
     * @see com.ibis.account.ao.IAJourAO#queryAccountJourPage(int, int, com.ibis.account.domain.AccountJour)
     */
    @Override
    public Paginable<AccountJour> queryAccountJourPage(int start, int limit,
            AccountJour condition) {
        return aJourBO.getPaginable(start, limit, condition);
    }

    @Override
    @Transactional
    public void doCheckJour(Long ajNo, String checkUser, Long amount) {
        AccountJour aJour = aJourBO.getAccountJour(ajNo);
        if (aJour == null) {
            throw new BizException("xn702514", "无对应交易流水");
        }
        if (!EAccountJourStatus.todoCheck.getCode().equalsIgnoreCase(
            aJour.getStatus())) {
            throw new BizException("xn702514", "交流流水不处于待对账状态");
        }
        if (amount != 0) {// 账不平
            aJourBO.doCheckAccount(ajNo, checkUser, EBoolean.NO);
            hlOrderBO.saveHLOrder(aJour.getAccountNumber(),
                EHLOrderType.TZ.getCode(), amount, checkUser,
                EHLOrderType.TZ.getValue());
        } else {// 账是平的
            aJourBO.doCheckAccount(ajNo, checkUser, EBoolean.YES);
        }

    }

    @Override
    @Transactional
    public void doApproveCheckJour(String hlNo, String approveUser,
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
                accountBO.refreshAmountWithoutCheck(hlOrder.getAccountNumber(),
                    hlOrder.getAmount(), EBizType.AJ_LB.getCode(),
                    hlOrder.getHlNo());

            }
        } else {
            if (EBoolean.YES.getCode().equalsIgnoreCase(approveResult)) { // 资金变动
                accountBO.refreshAmountWithoutCheck(hlOrder.getAccountNumber(),
                    -hlOrder.getAmount(), EBizType.AJ_HC.getCode(),
                    hlOrder.getHlNo());

            }
        }
    }
}
