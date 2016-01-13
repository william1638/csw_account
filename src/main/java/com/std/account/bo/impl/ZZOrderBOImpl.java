/**
 * @Title ZZOrderBOImpl.java 
 * @Package com.ibis.account.bo.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午3:24:54 
 * @version V1.0   
 */
package com.std.account.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.IZZOrderBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.core.OrderNoGenerater;
import com.std.account.dao.IZZOrderDAO;
import com.std.account.domain.ZZOrder;
import com.std.account.enums.EOrderStatus;
import com.std.account.enums.EOrderType;

/** 
 * @author: miyb 
 * @since: 2015-3-15 下午3:24:54 
 * @history:
 */
@Component
public class ZZOrderBOImpl extends PaginableBOImpl<ZZOrder> implements
        IZZOrderBO {
    @Autowired
    private IZZOrderDAO zzOrderDAO;

    /** 
     * @see com.ibis.account.bo.IZZOrderBO#getZZOrder(java.lang.String)
     */
    @Override
    public ZZOrder getZZOrder(String zzNo) {
        ZZOrder data = null;
        if (StringUtils.isNotBlank(zzNo)) {
            ZZOrder condition = new ZZOrder();
            condition.setZzNo(zzNo);
            data = zzOrderDAO.select(condition);
        }
        return data;
    }

    /** 
     * @see com.ibis.account.bo.IZZOrderBO#queryZZOrderList(com.ibis.account.domain.ZZOrder)
     */
    @Override
    public List<ZZOrder> queryZZOrderList(ZZOrder condition) {
        return zzOrderDAO.selectList(condition);
    }

    /** 
     * @see com.ibis.account.bo.IZZOrderBO#saveZZOrder(java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String saveZZOrder(String accountNumber, String direction,
            Long amount, String oppositeSystem, String oppositeAccount,
            String remark) {
        String zzNo = null;
        if (StringUtils.isNotBlank(accountNumber)
                && StringUtils.isNotBlank(direction) && amount != 0
                && StringUtils.isNotBlank(oppositeSystem)) {
            ZZOrder data = new ZZOrder();
            zzNo = OrderNoGenerater.generate(EOrderType.ZZ.getCode());
            data.setZzNo(zzNo);
            data.setStatus(EOrderStatus.PAY_YES.getCode());
            data.setDirection(direction);
            data.setAmount(amount);
            data.setCreateDatetime(new Date());
            data.setOppositeSystem(oppositeSystem);
            data.setOppositeAccount(oppositeAccount);
            data.setRemark(remark);
            data.setAccountNumber(accountNumber);
            zzOrderDAO.insert(data);
        }
        return zzNo;
    }

    @Override
    public List<ZZOrder> doStatisticsDvalue(String accountNumber) {
        List<ZZOrder> data = null;
        if (StringUtils.isNotBlank(accountNumber)) {
            ZZOrder condition = new ZZOrder();
            condition.setAccountNumber(accountNumber);
            data = zzOrderDAO.doStatisticsDvalue(condition);
        }
        return data;
    }

}
