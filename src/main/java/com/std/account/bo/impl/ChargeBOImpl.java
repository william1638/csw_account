package com.std.account.bo.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.IChargeBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.core.OrderNoGenerater;
import com.std.account.dao.IChargeDAO;
import com.std.account.domain.Charge;
import com.std.account.enums.EBoolean;
import com.std.account.enums.EChannel;
import com.std.account.enums.EOrderStatus;
import com.std.account.exception.BizException;

@Component
public class ChargeBOImpl extends PaginableBOImpl<Charge> implements IChargeBO {
    @Autowired
    private IChargeDAO chargeDAO;

    @Override
    public String saveChargeOffline(Charge data) {
        String code = null;
        if (StringUtils.isNotBlank(data.getFromAccountNumber())
                && StringUtils.isNotBlank(data.getAccountNumber())
                && data.getAmount() != 0) {
            code = OrderNoGenerater.generate("C");
            data.setCode(code);
            data.setChannel(EChannel.OFFLINE.getCode());
            data.setCreateDatetime(new Date());
            data.setStatus(EOrderStatus.todoAPPROVE.getCode());
            chargeDAO.insert(data);
        } else {
            throw new BizException("xn702000", "入参错误");
        }
        return code;
    }

    @Override
    public void refreshApproveOrder(String code, String approveUser,
            EBoolean approveResult, String approveNote, String refNo, Long fee) {
        if (StringUtils.isNotBlank(code) && StringUtils.isNotBlank(approveUser)
                && StringUtils.isNotBlank(approveNote)) {
            Charge data = new Charge();
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
            data.setRefNo(refNo);
            data.setFee(fee);
            chargeDAO.updateApproveOrder(data);
        } else {
            throw new BizException("xn702000", "入参错误");
        }

    }

    @Override
    public Charge getCharge(String code) {
        Charge data = null;
        if (StringUtils.isNotBlank(code)) {
            Charge condition = new Charge();
            condition.setCode(code);
            data = chargeDAO.select(condition);
        }
        return data;
    }
}
