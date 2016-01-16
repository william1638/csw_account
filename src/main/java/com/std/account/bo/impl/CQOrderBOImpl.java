/**
 * @Title CQOrderBOImpl.java 
 * @Package com.ibis.account.bo.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午3:22:19 
 * @version V1.0   
 */
package com.std.account.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.ICQOrderBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.core.OrderNoGenerater;
import com.std.account.dao.ICQOrderDAO;
import com.std.account.domain.CQOrder;
import com.std.account.enums.EBoolean;
import com.std.account.enums.EOrderStatus;

/** 
 * @author: miyb 
 * @since: 2015-3-15 下午3:22:19 
 * @history:
 */
@Component
public class CQOrderBOImpl extends PaginableBOImpl<CQOrder> implements
        ICQOrderBO {
    @Autowired
    private ICQOrderDAO cqOrderDAO;

    @Override
    public String saveCQOrder(String accountNumber, String direction,
            Long amount, String bankCode, String subbranch, String bankcardNo,
            String channel) {
        String cqNo = null;
        if (StringUtils.isNotBlank(accountNumber) && amount != 0
                && StringUtils.isNotBlank(bankCode)
                && StringUtils.isNotBlank(bankcardNo)
                && StringUtils.isNotBlank(channel)) {
            CQOrder data = new CQOrder();
            cqNo = OrderNoGenerater.generate("CQ");
            data.setCqNo(cqNo);
            data.setStatus(EOrderStatus.todoAPPROVE.getCode());
            data.setChannel(channel);
            data.setDirection(direction);
            data.setAmount(amount);
            data.setBankCode(bankCode);
            data.setSubbranch(subbranch);
            data.setBankcardNo(bankcardNo);
            data.setCreateDatetime(new Date());
            data.setAccountNumber(accountNumber);
            cqOrderDAO.insert(data);
        }
        return cqNo;
    }

    /** 
     * @see com.ibis.account.bo.ICQOrderBO#refreshApproveOrder(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public int refreshApproveOrder(String cqNo, String approveUser,
            String approveResult, String remark) {
        int count = 0;
        if (StringUtils.isNotBlank(cqNo) && StringUtils.isNotBlank(approveUser)
                && StringUtils.isNotBlank(approveResult)) {
            CQOrder data = new CQOrder();
            data.setCqNo(cqNo);
            if (EBoolean.YES.getCode().equalsIgnoreCase(approveResult)) {
                data.setStatus(EOrderStatus.APPROVE_YES.getCode());
            } else {
                data.setStatus(EOrderStatus.APPROVE_NO.getCode());
            }
            data.setApproveUser(approveUser);
            data.setApproveDatetime(new Date());
            data.setRemark(remark);
            count = cqOrderDAO.updateApproveOrder(data);
        }
        return count;
    }

    /** 
     * @see com.ibis.account.bo.ICQOrderBO#refreshPayOrder(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
     */
    @Override
    public int refreshPayOrder(String cqNo, String payUser, String payResult,
            String remark, String payNo, Long payFee, String workDate) {
        int count = 0;
        if (StringUtils.isNotBlank(cqNo) && StringUtils.isNotBlank(payUser)
                && StringUtils.isNotBlank(payResult)) {
            CQOrder data = new CQOrder();
            data.setCqNo(cqNo);
            if (EBoolean.YES.getCode().equalsIgnoreCase(payResult)) {
                data.setStatus(EOrderStatus.PAY_YES.getCode());
            } else {
                data.setStatus(EOrderStatus.PAY_NO.getCode());
            }
            data.setPayFee(payFee);
            data.setPayNo(payNo);
            data.setPayUser(payUser);
            data.setPayDatetime(new Date());
            data.setRemark(remark);
            data.setWorkDate(workDate);
            count = cqOrderDAO.updatePayOrder(data);
        }
        return count;
    }

    /** 
     * @see com.ibis.account.bo.ICQOrderBO#getCQOrder(java.lang.String)
     */
    @Override
    public CQOrder getCQOrder(String cqNo) {
        CQOrder data = null;
        if (StringUtils.isNotBlank(cqNo)) {
            CQOrder condition = new CQOrder();
            condition.setCqNo(cqNo);
            data = cqOrderDAO.select(condition);
        }
        return data;
    }

    /** 
     * @see com.ibis.account.bo.ICQOrderBO#queryCQOrderList(com.ibis.account.domain.CQOrder)
     */
    @Override
    public List<CQOrder> queryCQOrderList(CQOrder condition) {
        return cqOrderDAO.selectList(condition);
    }

}
