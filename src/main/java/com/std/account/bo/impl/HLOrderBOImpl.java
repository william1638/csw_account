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
import java.util.List;

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
import com.std.account.enums.EOrderType;

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

    /** 
     * @see com.ibis.account.bo.IHLOrderBO#saveHLOrder(java.lang.String, java.lang.Long, java.lang.String, java.lang.String)
     */
    @Override
    public String saveHLOrder(String accountNumber, Long amount,
            String applyUser, String applyNote) {
        String hlNo = null;
        if (StringUtils.isNotBlank(accountNumber) && amount != 0
                && StringUtils.isNotBlank(applyUser)
                && StringUtils.isNotBlank(applyNote)) {
            HLOrder data = new HLOrder();
            hlNo = OrderNoGenerater.generate(EOrderType.HL.getCode());
            data.setHlNo(hlNo);
            data.setStatus(EOrderStatus.UNAPPROVE.getCode());
            if (amount > 0) {
                data.setDirection(EDirection.PLUS.getCode());
                data.setAmount(amount);
            }
            if (amount < 0) {
                data.setDirection(EDirection.MINUS.getCode());
                data.setAmount(-amount);
            }
            data.setApplyUser(applyUser);
            data.setApplyNote(applyNote);
            data.setCreateDatetime(new Date());
            data.setAccountNumber(accountNumber);
            hlOrderDAO.insert(data);
        }
        return hlNo;
    }

    /** 
     * @see com.ibis.account.bo.IHLOrderBO#refreshApproveOrder(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public int refreshApproveOrder(String hlNo, String approveUser,
            String approveResult, String remark) {
        int count = 0;
        if (StringUtils.isNotBlank(hlNo) && StringUtils.isNotBlank(approveUser)
                && StringUtils.isNotBlank(approveResult)) {
            HLOrder data = new HLOrder();
            data.setHlNo(hlNo);
            if (EBoolean.YES.getCode().equalsIgnoreCase(approveResult)) {
                data.setStatus(EOrderStatus.APPROVE_YES.getCode());
            } else {
                data.setStatus(EOrderStatus.APPROVE_NO.getCode());
            }
            data.setApproveUser(approveUser);
            data.setApproveDatetime(new Date());
            data.setRemark(remark);
            count = hlOrderDAO.updateApproveOrder(data);

        }
        return count;
    }

    /** 
     * @see com.ibis.account.bo.IHLOrderBO#getHLOrder(java.lang.String)
     */
    @Override
    public HLOrder getHLOrder(String hlNo) {
        HLOrder data = null;
        if (StringUtils.isNotBlank(hlNo)) {
            HLOrder condition = new HLOrder();
            condition.setHlNo(hlNo);
            data = hlOrderDAO.select(condition);
        }
        return data;
    }

    /** 
     * @see com.ibis.account.bo.IHLOrderBO#queryHLOrderList(com.ibis.account.domain.HLOrder)
     */
    @Override
    public List<HLOrder> queryHLOrderList(HLOrder data) {
        return hlOrderDAO.selectList(data);
    }

}
