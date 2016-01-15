/**
 * @Title YTOrderBOImpl.java 
 * @Package com.ibis.account.bo.impl 
 * @Description 
 * @author miyb  
 * @date 2015-5-9 下午2:49:52 
 * @version V1.0   
 */
package com.std.account.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.IYTOrderBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.core.OrderNoGenerater;
import com.std.account.dao.IYTOrderDAO;
import com.std.account.domain.YTOrder;
import com.std.account.enums.EBoolean;
import com.std.account.enums.EOrderStatus;

/** 
 * @author: miyb 
 * @since: 2015-5-9 下午2:49:52 
 * @history:
 */
@Component
public class YTOrderBOImpl extends PaginableBOImpl<YTOrder> implements
        IYTOrderBO {
    @Autowired
    private IYTOrderDAO ytOrderDAO;

    /** 
     * @see com.ibis.account.bo.IYTOrderBO#saveYTOrder(java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
     */
    @Override
    public String saveYTOrder(String accountNumber, String status,
            String bizType, Long amount, String remark) {
        String ytNo = null;
        if (StringUtils.isNotBlank(accountNumber)
                && StringUtils.isNotBlank(status)
                && StringUtils.isNotBlank(bizType) && amount != 0) {
            YTOrder data = new YTOrder();
            ytNo = OrderNoGenerater.generate("YT");
            data.setYtNo(ytNo);
            data.setStatus(status);
            data.setBizType(bizType);
            data.setAmount(amount);
            data.setRemark(remark);

            data.setCreateDatetime(new Date());
            data.setAccountNumber(accountNumber);
            ytOrderDAO.insert(data);
        }
        return ytNo;
    }

    /** 
     * @see com.ibis.account.bo.IYTOrderBO#refreshApproveOrder(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public int refreshApproveOrder(String ytNo, String approveUser,
            String approveResult, String remark) {
        int count = 0;
        if (StringUtils.isNotBlank(ytNo) && StringUtils.isNotBlank(approveUser)
                && StringUtils.isNotBlank(approveResult)) {
            YTOrder data = new YTOrder();
            data.setYtNo(ytNo);
            if (EBoolean.YES.getCode().equalsIgnoreCase(approveResult)) {
                data.setStatus(EOrderStatus.APPROVE_YES.getCode());
            } else {
                data.setStatus(EOrderStatus.APPROVE_NO.getCode());
            }
            data.setApproveUser(approveUser);
            data.setApproveDatetime(new Date());
            data.setRemark(remark);
            count = ytOrderDAO.updateApproveOrder(data);
        }
        return count;
    }

    /** 
     * @see com.ibis.account.bo.IYTOrderBO#getYTOrder(java.lang.String)
     */
    @Override
    public YTOrder getYTOrder(String ytNo) {
        YTOrder data = null;
        if (StringUtils.isNotBlank(ytNo)) {
            YTOrder condition = new YTOrder();
            condition.setYtNo(ytNo);
            data = ytOrderDAO.select(condition);
        }
        return data;
    }

    /** 
     * @see com.ibis.account.bo.IYTOrderBO#queryYTOrderList(com.ibis.account.domain.YTOrder)
     */
    @Override
    public List<YTOrder> queryYTOrderList(YTOrder condition) {
        return ytOrderDAO.selectList(condition);
    }

}
