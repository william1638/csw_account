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
import com.std.account.domain.Account;
import com.std.account.domain.Jour;
import com.std.account.enums.EBizType;
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

    /** 
     * @see com.std.account.ao.IJourAO#doChangeAmount(java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.util.List, java.lang.String)
     */
    @Override
    public void doChangeAmount(String accountNumber, String bankcardNumber,
            Long transAmount, String bizType, String bizNote,
            List<String> channelTypeList, String systemCode) {
        EChannelType channelType = companyChannelBO.getBestChannel(systemCode,
            channelTypeList);
        Account account = accountBO.getAccount(systemCode, accountNumber);
        // 备注说明
        bizNote = EBizType.getBizTypeMap().get(bizType).getValue() + ":银行卡号["
                + bankcardNumber + "]划转金额";
        jourBO.addToChangeJour(systemCode, accountNumber,
            channelType.getCode(), EBizType.AJ_CZ.getCode(), bizNote,
            account.getAmount(), transAmount);
    }

    /** 
     * @see com.std.account.ao.IJourAO#doCallBackChange(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @Transactional
    public void doCallBackChange(String code, String rollbackResult,
            String rollbackUser, String rollbackNote) {
        Jour data = jourBO.getJour(code);
        jourBO.callBackChangeJour(code, rollbackUser, rollbackNote);
        if (EBizType.AJ_CZ.getCode().equals(data.getBizType())) {
            // 更新记录，账户加钱
            accountBO.transAmountNoJour(data.getSystemCode(), data
                .getAccountNumber(), EChannelType.getChannelTypeResultMap()
                .get(data.getChannelType()), null, data.getTransAmount(), data
                .getBizType(), data.getBizNote(), code);
        } else if (EBizType.AJ_QX.getCode().equals(data.getBizType())) {
            // 解冻记录，账户扣钱
            accountBO.unfrozenAmount(data.getSystemCode(),
                data.getAccountNumber(), data.getTransAmount(), code);
        }
    }
}
