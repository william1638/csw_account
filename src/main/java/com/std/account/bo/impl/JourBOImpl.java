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

import com.std.account.bo.IAccountBO;
import com.std.account.bo.IJourBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.core.OrderNoGenerater;
import com.std.account.dao.IJourDAO;
import com.std.account.domain.Jour;
import com.std.account.enums.EBizType;
import com.std.account.enums.EBoolean;
import com.std.account.enums.EChannelType;
import com.std.account.enums.EGeneratePrefix;
import com.std.account.enums.EJourStatus;

/** 
 * @author: miyb 
 * @since: 2015-3-15 下午3:22:07 
 * @history:
 */
@Component
public class JourBOImpl extends PaginableBOImpl<Jour> implements IJourBO {
    @Autowired
    private IJourDAO jourDAO;

    @Autowired
    private IAccountBO accountBO;

    @Override
    public Jour getJour(String code) {
        Jour data = null;
        if (StringUtils.isNotBlank(code)) {
            Jour condition = new Jour();
            condition.setCode(code);
            data = jourDAO.select(condition);
        }
        return data;
    }

    @Override
    public String addToChangeJour(String systemCode, String accountNumber,
            String channelType, String bizType, String bizNote, Long preAmount,
            Long transAmount) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AJour.getCode());
        Long postAmount = preAmount + transAmount;
        Jour jour = new Jour();
        jour.setSystemCode(systemCode);
        jour.setAccountNumber(accountNumber);
        jour.setChannelType(channelType);
        jour.setBizType(bizType);
        jour.setBizNote(bizNote);
        jour.setTransAmount(transAmount);

        jour.setPreAmount(preAmount);
        jour.setPostAmount(postAmount);
        jour.setCreateDatetime(new Date());
        jour.setStatus(EJourStatus.todoCallBack.getCode());
        jour.setWorkDate(null);
        jourDAO.insert(jour);
        // 取现冻结
        if (EBizType.AJ_QX.getCode().equals(bizType)) {
            if (EChannelType.CZB.getCode().equals(channelType)) {
                accountBO.frozenAmount(systemCode, accountNumber, transAmount,
                    code);
            }
        }
        return code;
    }

    /**
     * @see com.std.account.bo.IJourBO#callBackChangeJour(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public int callBackChangeJour(String code, String rollbackUser,
            String rollbackNote) {
        Jour data = new Jour();
        data.setCode(code);
        data.setStatus(EJourStatus.todoCheck.getCode());
        data.setRollbackUser(rollbackUser);
        data.setRollbackDatetime(new Date());
        data.setRemark(rollbackNote);
        return jourDAO.insert(data);
    }

    /** 
     * @see com.std.account.bo.IJourBO#addChangedJour(java.lang.String, java.lang.String, com.std.account.enums.EChannelType, java.lang.String, com.std.account.enums.EPayType, java.lang.String, java.lang.String, java.lang.Long, java.lang.Long)
     */
    @Override
    public String addChangedJour(String systemCode, String accountNumber,
            EChannelType channelType, String channelOrder, String bizType,
            String bizNote, Long preAmount, Long transAmount) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AJour.getCode());
        Long postAmount = preAmount + transAmount;
        Jour jour = new Jour();
        jour.setSystemCode(systemCode);
        jour.setAccountNumber(accountNumber);
        jour.setChannelType(channelType.getCode());
        jour.setBizType(bizType);
        jour.setBizNote(bizNote);
        jour.setTransAmount(transAmount);

        jour.setPreAmount(preAmount);
        jour.setPostAmount(postAmount);
        jour.setCreateDatetime(new Date());
        jour.setStatus(EJourStatus.todoCheck.getCode());
        jour.setWorkDate(null);
        jourDAO.insert(jour);
        return code;
    }

    /** 
     * @see com.std.account.bo.IJourBO#doCheckAccount(java.lang.String, com.std.account.enums.EBoolean, java.lang.String)
     */
    @Override
    public void doCheckAccount(String code, EBoolean checkResult,
            String checkUser) {
        // TODO Auto-generated method stub

    }

    /** 
     * @see com.std.account.bo.IJourBO#doAdjustAccount(java.lang.String, com.std.account.enums.EBoolean, java.lang.String)
     */
    @Override
    public void doAdjustAccount(String code, EBoolean adjustResult,
            String adjustUser) {
        // TODO Auto-generated method stub

    }

    // @Override
    // public String addTochangeJour(String systemCode, String accountName,
    // String accountNumber, EChannelType channelType, EPayType payType,
    // EBizType bizType, Long preAmount, Long transAmount) {
    // String order = OrderNoGenerater.generate(EGeneratePrefix.AJour
    // .getCode());
    // Long postAmount = preAmount + transAmount;
    // Jour jour = new Jour();
    // jour.setSystemCode(systemCode);
    // jour.setAccountName(accountName);
    // jour.setAccountNumber(accountNumber);
    // jour.setOrder(order);
    // jour.setChannelType(channelType.getCode());
    //
    // jour.setPayType(payType.getCode());
    // jour.setPayOrder(null);
    // jour.setBizType(bizType.getCode());
    // jour.setBizNote(bizType.getValue());
    // jour.setTransAmount(transAmount);
    //
    // jour.setPreAmount(preAmount);
    // jour.setPostAmount(postAmount);
    // jour.setTransDatetime(null);
    // jour.setStatus(EAccountJourStatus.todoCallBack.getCode());
    // jour.setWorkDate(null);
    // jourDAO.insert(jour);
    // return order;
    // }
    //
    // @Override
    // public String addChangedJour(String systemCode, String accountName,
    // String accountNumber, EChannelType channelType, EPayType payType,
    // String bizType, String bizNote, Long preAmount, Long transAmount) {
    // String order = OrderNoGenerater.generate(EGeneratePrefix.AJour
    // .getCode());
    // Long postAmount = preAmount + transAmount;
    // Jour jour = new Jour();
    // jour.setSystemCode(systemCode);
    // jour.setAccountNumber(accountNumber);
    // jour.setChannelType(channelType.getCode());
    // jour.setBizType(bizType);
    // jour.setBizNote(bizNote);
    // jour.setTransAmount(transAmount);
    //
    // jour.setPreAmount(preAmount);
    // jour.setPostAmount(postAmount);
    // jour.setTransDatetime(new Date());
    // jour.setStatus(EAccountJourStatus.todoCheck.getCode());
    // jour.setWorkDate(DateUtil
    // .getToday(DateUtil.DB_DATE_FORMAT_STRING));
    // jourDAO.insert(jour);
    // return order;
    // }
    //
    // @Override
    // public void doTransAccount(String order, String payOrder) {
    // if (StringUtils.isNotBlank(order) && StringUtils.isNotBlank(payOrder)) {
    // Jour data = new Jour();
    // data.setOrder(order);
    // data.setPayOrder(payOrder);
    // data.setTransDatetime(new Date());
    // data.setStatus(EAccountJourStatus.todoCheck.getCode());
    // jourDAO.updateTrans(data);
    // }
    // }
    //
    // @Override
    // public void doCheckAccount(String order, String checkUser,
    // EBoolean checkResult) {
    // if (StringUtils.isNotBlank(order)) {
    // Jour data = new Jour();
    // data.setOrder(order);
    // data.setCheckUser(checkUser);
    // data.setCheckDatetime(new Date());
    // if (EBoolean.YES.getCode().equalsIgnoreCase(checkResult.getCode())) {
    // data.setStatus(EAccountJourStatus.Checked_YES.getCode());
    // } else {
    // data.setStatus(EAccountJourStatus.Checked_NO.getCode());
    // }
    // jourDAO.updateCheck(data);
    // }
    // }
    //
    // @Override
    // public void doAdjustAccount(String order, String adjustUser) {
    // if (StringUtils.isNotBlank(order)) {
    // Jour data = new Jour();
    // data.setOrder(order);
    // data.setStatus(EAccountJourStatus.Adjusted.getCode());
    // data.setAdjustUser(adjustUser);
    // data.setAdjustDatetime(new Date());
    // jourDAO.updateAdjust(data);
    // }
    // }

}
