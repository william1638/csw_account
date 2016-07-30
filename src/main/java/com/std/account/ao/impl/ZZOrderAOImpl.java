/**
 * @Title ZZOrderAOImpl.java 
 * @Package com.ibis.account.ao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-17 下午9:35:01 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.account.ao.IZZOrderAO;
import com.std.account.bo.IAccountBO;
import com.std.account.bo.IUserBO;
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
    IUserBO userBO;

    @Autowired
    IAccountBO accountBO;

    @Autowired
    IZZOrderBO zzOrderBO;

    @Override
    public Paginable<ZZOrder> queryZZOrderPage(int start, int limit,
            ZZOrder condition) {
        return zzOrderBO.getPaginable(start, limit, condition);
    }

    @Override
    @Transactional
    public String doTransferOSS(String accountNumber, String direction,
            Long amount, Long fee, String remark) {
        Account account = accountBO.getAccount(accountNumber);
        if (account == null) {
            throw new BizException("li01004", accountNumber + "账户不存在");
        }
        return doTransfer(accountNumber, direction, amount, fee, remark);
    }

    @Override
    @Transactional
    public String doTransferFRONT(String accountNumber, String direction,
            Long amount, Long fee, String remark, String tradePwd) {
        Account account = accountBO.getAccount(accountNumber);
        if (account == null) {
            throw new BizException("li01004", accountNumber + "账户不存在");
        }
        // 校验交易密码
        userBO.checkTradePwd(account.getUserId(), tradePwd);
        return doTransfer(accountNumber, direction, amount, fee, remark);
    }

    private String doTransfer(String accountNumber, String direction,
            Long amount, Long fee, String remark) {
        EBizType bizType = null;
        Long transAmount = null;
        EDirection dir = null;
        if (EDirection.PLUS.getCode().equalsIgnoreCase(direction)) {
            bizType = EBizType.AJ_MH;
            transAmount = amount;
            dir = EDirection.PLUS;
        }
        if (EDirection.MINUS.getCode().equalsIgnoreCase(direction)) {
            bizType = EBizType.AJ_XF;
            transAmount = -amount;
            dir = EDirection.MINUS;
        }
        String zzNo = zzOrderBO.saveZZOrder(accountNumber, dir, amount, fee,
            remark);
        // 资金变动
        accountBO.refreshAmount(accountNumber, transAmount, zzNo, bizType);
        return zzNo;
    }
}
