/**
 * @Title BankCardAOImpl.java 
 * @Package com.ibis.pz.user.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-8 上午10:52:06 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.account.ao.IBankCardAO;
import com.std.account.bo.IBankCardBO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.BankCard;
import com.std.account.exception.BizException;

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
    public List<BankCard> queryBankCardList(String userId) {
        return bankCardBO.queryBankCardList(userId);
    }

    @Override
    @Transactional
    public void doBindBandCard(String userId, String type, String bankCode,
            String bankName, String bankCardNo, String subbranch,
            String bindMobile) {
        bankCardBO.saveBankCard(userId, type, bankCode, bankName, bankCardNo,
            subbranch, bindMobile);

    }

    @Override
    public Paginable<BankCard> queryBankCardPage(int start, int limit,
            BankCard condition) {
        return bankCardBO.getPaginable(start, limit, condition);
    }

    @Override
    public void dropBankCard(Long id) {
        if (!bankCardBO.isBankCardExist((id))) {
            throw new BizException("xn702000", "删除通道不存在！");
        }
        bankCardBO.removeBankCard(id);

    }

    @Override
    public void doRebindBankCard(Long id, String userId, String bankCode,
            String bankName, String bankCardNo, String subbranch,
            String bindMobile) {
        bankCardBO.refreshBankCard(id, userId, bankCode, bankName, bankCardNo,
            subbranch, bindMobile);

    }

}
