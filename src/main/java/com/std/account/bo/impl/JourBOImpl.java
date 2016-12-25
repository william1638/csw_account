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
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.IAccountBO;
import com.std.account.bo.IJourBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.core.OrderNoGenerater;
import com.std.account.dao.IJourDAO;
import com.std.account.domain.Account;
import com.std.account.domain.Jour;
import com.std.account.enums.EBizType;
import com.std.account.enums.EBoolean;
import com.std.account.enums.EChannelType;
import com.std.account.enums.EGeneratePrefix;
import com.std.account.enums.EJourStatus;
import com.std.account.exception.BizException;

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
    public Jour getJour(String code, String systemCode) {
        Jour data = null;
        if (StringUtils.isNotBlank(code)) {
            Jour condition = new Jour();
            condition.setCode(code);
            condition.setSystemCode(systemCode);
            data = jourDAO.select(condition);
            if (data == null) {
                throw new BizException("xn000000", "单号不存在");
            }
        }
        return data;
    }

    @Override
    public String addToChangeJour(String systemCode, String accountNumber,
            String channelType, String bizType, String bizNote, Long preAmount,
            Long transAmount) {
        Account account = accountBO.getAccount(systemCode, accountNumber);
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AJour.getCode());
        Long postAmount = preAmount + transAmount;
        Jour data = new Jour();
        data.setCode(code);
        data.setUserId(account.getUserId());
        data.setRealName(account.getRealName());
        data.setAccountNumber(accountNumber);
        data.setChannelType(channelType);
        data.setBizType(bizType);
        data.setBizNote(bizNote);
        data.setTransAmount(transAmount);
        data.setPreAmount(preAmount);
        data.setPostAmount(postAmount);
        data.setCreateDatetime(new Date());
        data.setStatus(EJourStatus.todoCallBack.getCode());
        data.setWorkDate(null);
        data.setSystemCode(systemCode);
        jourDAO.insert(data);
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
    public int callBackChangeJour(String code, String rollBackResult,
            String rollbackUser, String rollbackNote) {
        Jour data = new Jour();
        data.setCode(code);
        EJourStatus eJourStatus = EJourStatus.todoCheck;
        if (EBoolean.NO.getCode().equals(rollBackResult)) {
            eJourStatus = EJourStatus.callBack_NO;
        }
        data.setStatus(eJourStatus.getCode());
        data.setRollbackUser(rollbackUser);
        data.setRollbackDatetime(new Date());
        data.setRemark(rollbackNote);
        return jourDAO.updateCallback(data);
    }

    /** 
     * @see com.std.account.bo.IJourBO#addChangedJour(java.lang.String, java.lang.String, com.std.account.enums.EChannelType, java.lang.String, com.std.account.enums.EPayType, java.lang.String, java.lang.String, java.lang.Long, java.lang.Long)
     */
    @Override
    public String addChangedJour(String systemCode, String accountNumber,
            EChannelType channelType, String channelOrder, String bizType,
            String bizNote, Long preAmount, Long transAmount) {
        Account account = accountBO.getAccount(systemCode, accountNumber);
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AJour.getCode());
        Long postAmount = preAmount + transAmount;
        Jour data = new Jour();
        data.setSystemCode(systemCode);
        data.setUserId(account.getUserId());
        data.setRealName(account.getRealName());
        data.setAccountNumber(accountNumber);
        data.setChannelType(channelType.getCode());
        data.setBizType(bizType);
        data.setBizNote(bizNote);
        data.setTransAmount(transAmount);

        data.setPreAmount(preAmount);
        data.setPostAmount(postAmount);
        data.setCreateDatetime(new Date());
        data.setStatus(EJourStatus.todoCheck.getCode());
        data.setWorkDate(null);
        jourDAO.insert(data);
        return code;
    }

    @Override
    public void doCheckJour(String code, EBoolean checkResult,
            String checkUser, String checkNote) {
        Jour data = new Jour();
        data.setCode(code);
        String status = EJourStatus.Checked_YES
        if(EBoolean.YES.equals(checkResult)){
            
        }

    }

    /** 
     * @see com.std.account.bo.IJourBO#doAdjustAccount(java.lang.String, com.std.account.enums.EBoolean, java.lang.String)
     */
    @Override
    public void doAdjustAccount(String code, EBoolean adjustResult,
            String adjustUser) {
        // TODO Auto-generated method stub

    }

    /** 
     * @see com.std.account.bo.IJourBO#queryJourList(com.std.account.domain.Jour)
     */
    @Override
    public List<Jour> queryJourList(Jour condition) {
        return jourDAO.selectList(condition);
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
