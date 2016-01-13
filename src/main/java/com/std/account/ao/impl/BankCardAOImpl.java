/**
 * @Title BankCardAOImpl.java 
 * @Package com.ibis.pz.user.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-8 上午10:52:06 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.account.ao.IBankCardAO;
import com.std.account.bo.IBankCardBO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.BankCard;

/** 
 * @author: miyb 
 * @since: 2015-3-8 上午10:52:06 
 * @history:
 */
@Service
public class BankCardAOImpl implements IBankCardAO {
    @Autowired
    IBankCardBO bankCardBO;

    @Override
    public BankCard getBankCard(String userId) {
        return bankCardBO.getBankCard(userId);
    }

    @Override
    @Transactional
    public boolean doBindBandCard(String userId, String bankCode,
            String bankName, String bankCardNo, String subbranch,
            String bindMobile) {
        BankCard dbBankCard = bankCardBO.getBankCard(userId);
        if (dbBankCard != null) {// 银行卡已经绑定
            // 更新银行卡信息
            bankCardBO.refreshBankCard(dbBankCard, bankCode, bankName,
                bankCardNo, subbranch, bindMobile);

        } else {
            // 绑定银行卡信息
            bankCardBO.saveBankCard(userId, bankCode, bankName, bankCardNo,
                subbranch, bindMobile);
        }
        return true;
    }

    @Override
    public Paginable<BankCard> queryBankCardPage(int start, int limit,
            BankCard condition) {
        return bankCardBO.getPaginable(start, limit, condition);
    }

}
