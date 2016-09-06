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

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.IZZOrderBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.core.OrderNoGenerater;
import com.std.account.dao.IZZOrderDAO;
import com.std.account.domain.ZZOrder;
import com.std.account.enums.EDirection;
import com.std.account.enums.EZzType;

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

    @Override
    public ZZOrder getZZOrder(String code) {
        ZZOrder data = null;
        if (StringUtils.isNotBlank(code)) {
            ZZOrder condition = new ZZOrder();
            condition.setCode(code);
            data = zzOrderDAO.select(condition);
        }
        return data;
    }

    @Override
    public String saveZZOrder(String accountNumber, EDirection direction,
            Long amount, Long fee, String remark) {
        String code = null;
        if (StringUtils.isNotBlank(accountNumber) && amount != 0
                && StringUtils.isNotBlank(remark)) {
            ZZOrder data = new ZZOrder();
            code = OrderNoGenerater.generate("ZZ");
            data.setCode(code);
            data.setType(EZzType.ZZ.getCode());
            data.setDirection(direction.getCode());
            data.setAmount(amount);
            data.setFee(fee);
            data.setRemark(remark);

            data.setCreateDatetime(new Date());
            data.setAccountNumber(accountNumber);
            zzOrderDAO.insert(data);
        }
        return code;
    }

    /** 
     * @see com.std.account.bo.IZZOrderBO#saveHzOrder(java.lang.String, java.lang.String, com.std.account.enums.EDirection, java.lang.Long, java.lang.Long, java.lang.String)
     */
    @Override
    public String saveHZOrder(String fromAccountNumber, String accountNumber,
            EDirection direction, Long amount, Long fee, String remark) {
        String code = null;
        if (StringUtils.isNotBlank(accountNumber) && amount != 0
                && StringUtils.isNotBlank(remark)) {
            ZZOrder data = new ZZOrder();

            code = OrderNoGenerater.generate("ZZ");
            data.setCode(code);
            data.setType(EZzType.HZ.getCode());
            data.setDirection(direction.getCode());
            data.setAmount(amount);
            data.setFee(fee);
            data.setRemark(remark);

            data.setCreateDatetime(new Date());
            data.setFromAccountNumber(fromAccountNumber);
            data.setAccountNumber(accountNumber);
            zzOrderDAO.insert(data);
        }
        return code;
    }

}
