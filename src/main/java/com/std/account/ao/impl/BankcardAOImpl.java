package com.std.account.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.account.ao.IBankcardAO;
import com.std.account.bo.IBankcardBO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.BankCard;
import com.std.account.exception.BizException;

/**
 * 
 * @author: asus 
 * @since: 2016年12月22日 下午5:35:09 
 * @history:
 */
@Service
public class BankcardAOImpl implements IBankcardAO {

    @Autowired
    private IBankcardBO bankcardBO;

    @Override
    public String addBankcard(BankCard data) {
        return bankcardBO.saveBankcard(data);
    }

    @Override
    public int editBankcard(BankCard data) {
        if (!bankcardBO.isBankcardExist(data.getCode())) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return bankcardBO.refreshBankcard(data);
    }

    @Override
    public int dropBankcard(String code) {
        if (!bankcardBO.isBankcardExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return bankcardBO.removeBankcard(code);
    }

    @Override
    public Paginable<BankCard> queryBankcardPage(int start, int limit,
            BankCard condition) {
        return bankcardBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<BankCard> queryBankcardList(BankCard condition) {
        return bankcardBO.queryBankcardList(condition);
    }

    @Override
    public BankCard getBankcard(String code) {
        return bankcardBO.getBankcard(code);
    }
}
