package com.std.account.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.IBankcardBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.core.OrderNoGenerater;
import com.std.account.dao.IBankCardDAO;
import com.std.account.domain.BankCard;
import com.std.account.enums.EBoolean;
import com.std.account.enums.EGeneratePrefix;
import com.std.account.exception.BizException;

/**
 * 
 * @author: asus 
 * @since: 2016年12月22日 下午4:32:05 
 * @history:
 */
@Component
public class BankcardBOImpl extends PaginableBOImpl<BankCard> implements
        IBankcardBO {

    @Autowired
    private IBankCardDAO bankcardDAO;

    @Override
    public boolean isBankcardExist(String code) {
        BankCard condition = new BankCard();
        condition.setCode(code);
        if (bankcardDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveBankcard(BankCard data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater
                .generate(EGeneratePrefix.BankCard.getCode());
            data.setCode(code);
            data.setStatus(EBoolean.YES.getCode());
            data.setCreateDatetime(new Date());
            bankcardDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeBankcard(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            BankCard data = new BankCard();
            data.setCode(code);
            count = bankcardDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshBankcard(BankCard data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = bankcardDAO.update(data);
        }
        return count;
    }

    @Override
    public List<BankCard> queryBankcardList(BankCard condition) {
        return bankcardDAO.selectList(condition);
    }

    @Override
    public BankCard getBankcard(String code) {
        BankCard data = null;
        if (StringUtils.isNotBlank(code)) {
            BankCard condition = new BankCard();
            condition.setCode(code);
            data = bankcardDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "�� ��Ų�����");
            }
        }
        return data;
    }
}
