/**
 * @Title ZZOrderAOImpl.java 
 * @Package com.ibis.account.ao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-17 下午9:35:01 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.account.ao.IZZOrderAO;
import com.std.account.bo.IAccountBO;
import com.std.account.bo.IZZOrderBO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.Account;
import com.std.account.domain.ZZOrder;
import com.std.account.enums.EBizType;
import com.std.account.enums.EDirection;
import com.std.account.exception.BizException;

/** 
 * @author: miyb 
 * @since: 2015-3-17 下午9:35:01 
 * @history:
 */
@Service
public class ZZOrderAOImpl implements IZZOrderAO {
    @Autowired
    IAccountBO accountBO;

    @Autowired
    IZZOrderBO zzOrderBO;

    /** 
     * @see com.ibis.account.ao.IZZOrderAO#doTransfer(java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @Transactional
    public String doTransfer(String userId, Long amount, String oppositeSystem,
            String oppositeAccount, String remark) {
        Account account = accountBO.getAccountByUserId(userId);
        if (account == null) {
            throw new BizException("li01004", userId + "用户不存在");
        }
        String accountNumber = account.getAccountNumber();
        String bizType = null;
        String direction = null;
        if (amount > 0) {
            bizType = EBizType.AJ_ZR.getCode();
            direction = EDirection.PLUS.getCode();
        }
        if (amount < 0) {
            bizType = EBizType.AJ_ZC.getCode();
            direction = EDirection.MINUS.getCode();
        }
        String zzNo = zzOrderBO.saveZZOrder(accountNumber, direction, amount,
            oppositeSystem, oppositeAccount, remark);
        // 资金变动
        accountBO.refreshAmount(accountNumber, amount, bizType, zzNo, remark);
        return zzNo;
    }

    /** 
     * @see com.ibis.account.ao.IZZOrderAO#queryZZOrderPage(int, int, com.ibis.account.domain.ZZOrder)
     */
    @Override
    public Paginable<ZZOrder> queryZZOrderPage(int start, int limit,
            ZZOrder condition) {
        return zzOrderBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<ZZOrder> doStatisticsDvalue(String accountNumber) {
        return zzOrderBO.doStatisticsDvalue(accountNumber);
    }

}
