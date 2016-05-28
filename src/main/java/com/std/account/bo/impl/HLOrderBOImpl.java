/**
 * @Title HLOrderBOImpl.java 
 * @Package com.ibis.account.bo.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午3:22:32 
 * @version V1.0   
 */
package com.std.account.bo.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.IHLOrderBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.core.OrderNoGenerater;
import com.std.account.dao.IHLOrderDAO;
import com.std.account.domain.HLOrder;
import com.std.account.enums.EBoolean;
import com.std.account.enums.EDirection;
import com.std.account.enums.EOrderStatus;

/** 
 * @author: miyb 
 * @since: 2015-3-15 下午3:22:32 
 * @history:
 */
@Component
public class HLOrderBOImpl extends PaginableBOImpl<HLOrder> implements
        IHLOrderBO {
    @Autowired
    private IHLOrderDAO hlOrderDAO;

    @Override
    public String saveHLOrder(String accountNumber, EDirection direction,
            Long amount, String applyUser, String applyNote) {
        String code = null;
        if (StringUtils.isNotBlank(accountNumber) && amount != 0
                && StringUtils.isNotBlank(applyUser)
                && StringUtils.isNotBlank(applyNote)) {
            HLOrder data = new HLOrder();
            code = OrderNoGenerater.generate("HL");
            data.setCode(code);
            data.setDirection(direction.getCode());
            data.setAmount(amount);
            data.setStatus(EOrderStatus.todoAPPROVE.getCode());
            data.setApplyUser(applyUser);

            data.setApplyNote(applyNote);
            data.setApplyDatetime(new Date());
            data.setAccountNumber(accountNumber);
            hlOrderDAO.insert(data);
        }
        return code;
    }

    @Override
    public void refreshApproveOrder(String code, String approveUser,
            EBoolean approveResult, String approveNote) {
        if (StringUtils.isNotBlank(code) && StringUtils.isNotBlank(approveUser)
                && StringUtils.isNotBlank(approveNote)) {
            HLOrder data = new HLOrder();
            data.setCode(code);
            if (EBoolean.YES.getCode()
                .equalsIgnoreCase(approveResult.getCode())) {
                data.setStatus(EOrderStatus.APPROVE_YES.getCode());
            } else {
                data.setStatus(EOrderStatus.APPROVE_NO.getCode());
            }
            data.setApproveUser(approveUser);
            data.setApproveNote(approveNote);
            data.setApproveDatetime(new Date());
            hlOrderDAO.updateApproveOrder(data);
        }

    }

    @Override
    public HLOrder getHLOrder(String code) {
        HLOrder data = null;
        if (StringUtils.isNotBlank(code)) {
            HLOrder condition = new HLOrder();
            condition.setCode(code);
            data = hlOrderDAO.select(condition);
        }
        return data;
    }

}
