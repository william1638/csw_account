/**
 * @Title XNBOrderBOImpl.java 
 * @Package com.std.account.bo.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午3:22:46 
 * @version V1.0   
 */
package com.std.account.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.IXNBOrderBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.core.OrderNoGenerater;
import com.std.account.dao.IXNBOrderDAO;
import com.std.account.domain.XNBOrder;
import com.std.account.enums.EBoolean;
import com.std.account.enums.EOrderStatus;
import com.std.account.enums.EScoreType;
import com.std.account.exception.BizException;

/** 
 * @author: miyb 
 * @since: 2015-3-15 下午3:22:46 
 * @history:
 */
@Component
public class XNBOrderBOImpl extends PaginableBOImpl<XNBOrder>
        implements IXNBOrderBO {
    @Autowired
    private IXNBOrderDAO xnbOrderDAO;

    /** 
     * @see com.std.account.bo.IXNBOrderBO#saveXNBOrder(java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.Long, java.lang.String)
     */
    @Override
    public String saveXNBOrder(String accountNumber, String status, String type,
            Long score, Long amount, String remark) {
        String xnbNo = null;
        if (StringUtils.isNotBlank(accountNumber)
                && StringUtils.isNotBlank(type) && amount > 0 && score > 0) {
            if (EScoreType.getScoreTypeMap().get(type) == null) {
                throw new BizException("li779001", "type不在程序所能支持种类");
            }
            XNBOrder data = new XNBOrder();
            xnbNo = OrderNoGenerater.generate("XNB");
            data.setXnbNo(xnbNo);
            data.setStatus(status);
            data.setType(type);
            data.setScore(score);
            data.setAmount(amount);
            data.setRemark(remark);
            data.setCreateDatetime(new Date());
            data.setAccountNumber(accountNumber);
            xnbOrderDAO.insert(data);
        }
        return xnbNo;
    }

    /** 
     * @see com.std.account.bo.IXNBOrderBO#refreshApproveOrder(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public int refreshApproveOrder(String xnbNo, String approveUser,
            String approveResult, String remark) {
        int count = 0;
        if (StringUtils.isNotBlank(xnbNo) && StringUtils.isNotBlank(approveUser)
                && StringUtils.isNotBlank(approveResult)) {
            XNBOrder data = new XNBOrder();
            data.setXnbNo(xnbNo);
            if (EBoolean.YES.getCode().equalsIgnoreCase(approveResult)) {
                data.setStatus(EOrderStatus.APPROVE_YES.getCode());
            } else {
                data.setStatus(EOrderStatus.APPROVE_NO.getCode());
            }
            data.setApproveUser(approveUser);
            data.setApproveDatetime(new Date());
            data.setRemark(remark);
            count = xnbOrderDAO.updateApproveOrder(data);

        }
        return count;
    }

    /** 
     * @see com.std.account.bo.IXNBOrderBO#getXNBOrder(java.lang.String)
     */
    @Override
    public XNBOrder getXNBOrder(String xnbNo) {
        XNBOrder data = null;
        if (StringUtils.isNotBlank(xnbNo)) {
            XNBOrder condition = new XNBOrder();
            condition.setXnbNo(xnbNo);
            data = xnbOrderDAO.select(condition);
        }
        return data;
    }

    /** 
     * @see com.std.account.bo.IXNBOrderBO#queryXNBOrderList(com.std.account.domain.XNBOrder)
     */
    @Override
    public List<XNBOrder> queryXNBOrderList(XNBOrder condition) {
        return xnbOrderDAO.selectList(condition);
    }

}
