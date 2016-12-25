/**
 * @Title IJourAOImpl.java 
 * @Package com.std.account.ao.impl 
 * @Description 
 * @author xieyj  
 * @date 2016年12月23日 下午9:16:58 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.account.ao.IJourAO;
import com.std.account.bo.IAccountBO;
import com.std.account.bo.ICompanyChannelBO;
import com.std.account.bo.IJourBO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.Account;
import com.std.account.domain.Jour;
import com.std.account.enums.EBizType;
import com.std.account.enums.EBoolean;
import com.std.account.enums.EChannelType;

/** 
 * @author: xieyj 
 * @since: 2016年12月23日 下午9:16:58 
 * @history:
 */
@Service
public class JourAOImpl implements IJourAO {

    @Autowired
    private ICompanyChannelBO companyChannelBO;

    @Autowired
    private IJourBO jourBO;

    @Autowired
    private IAccountBO accountBO;

    /*
     * 外部账支付：1、产生支付申请订单；2、返回支付链接；
     */
    @Override
    @Transactional
    public String doChangeAmount(String accountNumber, String bankcardNumber,
            Long transAmount, String bizType, String bizNote,
            List<String> channelTypeList, String systemCode) {
        String payUrl = null;
        EChannelType channelType = companyChannelBO.getBestChannel(systemCode,
            channelTypeList);
        Account account = accountBO.getAccount(systemCode, accountNumber);
        bizNote = EBizType.getBizTypeMap().get(bizType).getValue() + ":银行卡号["
                + bankcardNumber + "]划转金额";
        jourBO.addToChangeJour(systemCode, accountNumber,
            channelType.getCode(), bizType, bizNote, account.getAmount(),
            transAmount);
        return payUrl;
    }

    /*
     * 回调方法： 1、审核通过扣除金额；审核不通过，资金原路返回
     */
    @Override
    @Transactional
    public void doCallBackChange(String code, String rollbackResult,
            String rollbackUser, String rollbackNote, String systemCode) {
        Jour data = jourBO.getJour(code, systemCode);
        if (EBoolean.YES.getCode().equals(rollbackResult)) {
            if (EBizType.AJ_CZ.getCode().equals(data.getBizType())) {
                accountBO.transAmountNotJour(data.getSystemCode(), data
                    .getAccountNumber(), EChannelType.getChannelTypeResultMap()
                    .get(data.getChannelType()), null, data.getTransAmount(),
                    data.getBizType(), data.getBizNote(), code);
            } else if (EBizType.AJ_QX.getCode().equals(data.getBizType())) {
                accountBO.unfrozenAmount(data.getSystemCode(),
                    EBoolean.YES.getCode(), data.getAccountNumber(),
                    data.getTransAmount(), code);
            }
        } else {
            accountBO.unfrozenAmount(data.getSystemCode(),
                EBoolean.NO.getCode(), data.getAccountNumber(),
                data.getTransAmount(), code);
        }
        jourBO.callBackChangeJour(code, rollbackResult, rollbackUser,
            rollbackNote);
    }

    /*
     * 人工调账： 1、判断流水账是否平
     */
    @Override
    public void checkJour(String code, String checkAmount, String checkUser,
            String checkNote, String systemCode) {
        //
    }

    @Override
    public Paginable<Jour> queryJourPage(int start, int limit, Jour condition) {
        return jourBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Jour> queryJourList(Jour condition) {
        return jourBO.queryJourList(condition);
    }

    /** 
     * @see com.std.account.ao.IJourAO#getJour(java.lang.String)
     */
    @Override
    public Jour getJour(String code, String systemCode) {
        return jourBO.getJour(code, systemCode);
    }
}
