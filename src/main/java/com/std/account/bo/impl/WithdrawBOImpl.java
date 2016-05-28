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

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.IWithdrawBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.core.OrderNoGenerater;
import com.std.account.dao.IWithdrawDAO;
import com.std.account.domain.Withdraw;
import com.std.account.enums.EBoolean;
import com.std.account.enums.EChannel;
import com.std.account.enums.EOrderStatus;
import com.std.account.enums.EToType;
import com.std.account.exception.BizException;

/** 
 * @author: miyb 
 * @since: 2015-3-15 下午3:22:19 
 * @history:
 */
@Component
public class WithdrawBOImpl extends PaginableBOImpl<Withdraw> implements
        IWithdrawBO {
    @Autowired
    private IWithdrawDAO withdrawDAO;

    @Override
    public String saveWithdrawOffline(String accountNumber, Long amount,
            EToType toType, String toCode) {
        String code = null;
        if (StringUtils.isNotBlank(accountNumber) && amount != 0
                && StringUtils.isNotBlank(toCode)) {
            Withdraw data = new Withdraw();
            code = OrderNoGenerater.generate("C");
            data.setCode(code);
            data.setToType(toType.getCode());
            data.setToCode(toCode);
            data.setChannel(EChannel.OFFLINE.getCode());

            data.setAmount(amount);
            data.setCreateDatetime(new Date());
            data.setStatus(EOrderStatus.todoAPPROVE.getCode());
            data.setAccountNumber(accountNumber);
            withdrawDAO.insert(data);
        } else {
            throw new BizException("xn702000", "入参有问题");
        }
        return code;
    }

    @Override
    public void refreshApproveOrder(String code, String approveUser,
            EBoolean approveResult, String approveNote) {
        if (StringUtils.isNotBlank(code) && StringUtils.isNotBlank(approveUser)
                && StringUtils.isNotBlank(approveNote)) {
            Withdraw data = new Withdraw();
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
            withdrawDAO.updateApproveOrder(data);
        } else {
            throw new BizException("xn702000", "入参有问题");
        }

    }

    @Override
    public void refreshPayOrder(String code, String payUser,
            EBoolean payResult, String payNote, String refNo, Long fee) {
        if (StringUtils.isNotBlank(code) && StringUtils.isNotBlank(payUser)
                && StringUtils.isNotBlank(payNote)) {
            Withdraw data = new Withdraw();
            data.setCode(code);
            if (EBoolean.YES.getCode().equalsIgnoreCase(payResult.getCode())) {
                data.setStatus(EOrderStatus.PAY_YES.getCode());
            } else {
                data.setStatus(EOrderStatus.PAY_NO.getCode());
            }
            data.setFee(fee);
            data.setRefNo(refNo);
            data.setPayUser(payUser);
            data.setPayNote(payNote);
            data.setPayDatetime(new Date());
            withdrawDAO.updatePayOrder(data);
        } else {
            throw new BizException("xn702000", "入参有问题");
        }
    }

    @Override
    public Withdraw getWithdraw(String code) {
        Withdraw data = null;
        if (StringUtils.isNotBlank(code)) {
            Withdraw condition = new Withdraw();
            condition.setCode(code);
            data = withdrawDAO.select(condition);
        }
        return data;
    }

}
