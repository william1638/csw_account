/**
 * @Title AJourBOImpl.java 
 * @Package com.ibis.account.bo.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午3:22:07 
 * @version V1.0   
 */
package com.std.account.bo.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.IAJourBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.common.DateUtil;
import com.std.account.core.OrderNoGenerater;
import com.std.account.dao.IAJourDAO;
import com.std.account.domain.AccountJour;
import com.std.account.enums.EAccountJourStatus;
import com.std.account.enums.EBizType;
import com.std.account.enums.EBoolean;
import com.std.account.enums.EChannelType;
import com.std.account.enums.EGeneratePrefix;
import com.std.account.enums.EPayType;

/** 
 * @author: miyb 
 * @since: 2015-3-15 下午3:22:07 
 * @history:
 */
@Component
public class AJourBOImpl extends PaginableBOImpl<AccountJour> implements
        IAJourBO {
    @Autowired
    private IAJourDAO aJourDAO;

    @Override
    public AccountJour getAccountJour(String order) {
        AccountJour data = null;
        if (StringUtils.isNotBlank(order)) {
            AccountJour condition = new AccountJour();
            condition.setOrder(order);
            data = aJourDAO.select(condition);
        }
        return data;
    }

    @Override
    public String addTochangeJour(String systemCode, String accountName,
            String accountNumber, EChannelType channelType, EPayType payType,
            EBizType bizType, Long preAmount, Long transAmount) {
        String order = OrderNoGenerater.generate(EGeneratePrefix.AJour
            .getCode());
        Long postAmount = preAmount + transAmount;
        AccountJour accountJour = new AccountJour();
        accountJour.setSystemCode(systemCode);
        accountJour.setAccountName(accountName);
        accountJour.setAccountNumber(accountNumber);
        accountJour.setOrder(order);
        accountJour.setChannelType(channelType.getCode());

        accountJour.setPayType(payType.getCode());
        accountJour.setPayOrder(null);
        accountJour.setBizType(bizType.getCode());
        accountJour.setBizNote(bizType.getValue());
        accountJour.setTransAmount(transAmount);

        accountJour.setPreAmount(preAmount);
        accountJour.setPostAmount(postAmount);
        accountJour.setTransDatetime(null);
        accountJour.setStatus(EAccountJourStatus.todoCallBack.getCode());
        accountJour.setWorkDate(null);
        aJourDAO.insert(accountJour);
        return order;
    }

    @Override
    public String addChangedJour(String systemCode, String accountName,
            String accountNumber, EChannelType channelType, EPayType payType,
            String bizType, String bizNote, Long preAmount, Long transAmount) {
        String order = OrderNoGenerater.generate(EGeneratePrefix.AJour
            .getCode());
        Long postAmount = preAmount + transAmount;
        AccountJour accountJour = new AccountJour();
        accountJour.setSystemCode(systemCode);
        accountJour.setAccountName(accountName);
        accountJour.setAccountNumber(accountNumber);
        accountJour.setOrder(order);
        accountJour.setChannelType(channelType.getCode());

        accountJour.setPayType(payType.getCode());
        accountJour.setPayOrder("NA");
        accountJour.setBizType(bizType);
        accountJour.setBizNote(bizNote);
        accountJour.setTransAmount(transAmount);

        accountJour.setPreAmount(preAmount);
        accountJour.setPostAmount(postAmount);
        accountJour.setTransDatetime(new Date());
        accountJour.setStatus(EAccountJourStatus.todoCheck.getCode());
        accountJour.setWorkDate(DateUtil
            .getToday(DateUtil.DB_DATE_FORMAT_STRING));
        aJourDAO.insert(accountJour);
        return order;
    }

    @Override
    public void doTransAccount(String order, String payOrder) {
        if (StringUtils.isNotBlank(order) && StringUtils.isNotBlank(payOrder)) {
            AccountJour data = new AccountJour();
            data.setOrder(order);
            data.setPayOrder(payOrder);
            data.setTransDatetime(new Date());
            data.setStatus(EAccountJourStatus.todoCheck.getCode());
            aJourDAO.updateTrans(data);
        }
    }

    @Override
    public void doCheckAccount(String order, String checkUser,
            EBoolean checkResult) {
        if (StringUtils.isNotBlank(order)) {
            AccountJour data = new AccountJour();
            data.setOrder(order);
            data.setCheckUser(checkUser);
            data.setCheckDatetime(new Date());
            if (EBoolean.YES.getCode().equalsIgnoreCase(checkResult.getCode())) {
                data.setStatus(EAccountJourStatus.Checked_YES.getCode());
            } else {
                data.setStatus(EAccountJourStatus.Checked_NO.getCode());
            }
            aJourDAO.updateCheck(data);
        }
    }

    @Override
    public void doAdjustAccount(String order, String adjustUser) {
        if (StringUtils.isNotBlank(order)) {
            AccountJour data = new AccountJour();
            data.setOrder(order);
            data.setStatus(EAccountJourStatus.Adjusted.getCode());
            data.setAdjustUser(adjustUser);
            data.setAdjustDatetime(new Date());
            aJourDAO.updateAdjust(data);
        }
    }

}
