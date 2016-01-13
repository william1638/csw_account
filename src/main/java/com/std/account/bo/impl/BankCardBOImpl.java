/**
 * @Title BankCardBOImpl.java 
 * @Package com.ibis.pz.user.impl 
 * @Description 
 * @author luoqi  
 * @date 2015年3月8日 上午11:51:56 
 * @version V1.0   
 */
package com.std.account.bo.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.IBankCardBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.dao.IBankCardDAO;
import com.std.account.domain.BankCard;
import com.std.account.enums.EBankCardStatus;
import com.std.account.exception.BizException;

/** 
 * @author: luoqi 
 * @since: 2015年3月8日 上午11:51:56 
 * @history:
 */
@Component
public class BankCardBOImpl extends PaginableBOImpl<BankCard> implements
        IBankCardBO {
    @Autowired
    private IBankCardDAO bankCardDAO;

    @Override
    public void saveBankCard(String userId, String bankCode, String bankName,
            String bankCardNo, String subbranch, String bindMobile) {
        BankCard data = new BankCard();
        data.setUserId(userId);
        data.setBankCode(bankCode);
        data.setBankName(bankName);
        data.setBankCardNo(bankCardNo);
        data.setSubbranch(subbranch);
        data.setBindMobile(bindMobile);
        data.setStatus(EBankCardStatus.TOCONFIRM.getCode());
        data.setCreateDatetime(new Date());
        bankCardDAO.insert(data);
    }

    @Override
    public void refreshBankCard(BankCard dbBankCard, String bankCode,
            String bankName, String bankCardNo, String subbranch,
            String bindMobile) {
        if (dbBankCard == null) {
            throw new BizException("xn702006", "该卡不存在");
        }
        // if (dbBankCard.getBankCardNo().equals(bankCardNo)) {
        // throw new BizException("xn702006", "该卡已存在,无需重新绑定");
        // }
        BankCard data = new BankCard();
        data.setUserId(dbBankCard.getUserId());
        data.setBankCode(bankCode);
        data.setBankName(bankName);
        data.setBankCardNo(bankCardNo);
        data.setSubbranch(subbranch);
        data.setBindMobile(bindMobile);
        data.setStatus(EBankCardStatus.TOCONFIRM.getCode());
        data.setUpdateDatetime(new Date());
        bankCardDAO.update(data);
    }

    /** 
     * @see com.ibis.pz.user.IBankCardBO#getBankCard(java.lang.String)
     */
    @Override
    public BankCard getBankCard(String userId) {
        BankCard data = null;
        if (StringUtils.isNotBlank(userId)) {
            BankCard condition = new BankCard();
            condition.setUserId(userId);
            data = bankCardDAO.select(condition);
        }
        return data;
    }

    @Override
    public void refreshStatus(String bankCode, String bankcardNo,
            EBankCardStatus status) {
        BankCard data = new BankCard();
        data.setBankCode(bankCode);
        data.setBankCardNo(bankcardNo);
        data.setStatus(status.getCode());
        data.setUpdateDatetime(new Date());
        bankCardDAO.updateStatus(data);
    }

}
