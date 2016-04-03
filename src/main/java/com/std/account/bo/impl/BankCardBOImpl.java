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
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.IBankCardBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.dao.IBankCardDAO;
import com.std.account.domain.BankCard;
import com.std.account.enums.EBankCardStatus;
import com.std.account.enums.EBankCardType;
import com.std.account.enums.EBoolean;

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
    public void saveBankCard(String ownerId, String ownerName, String type,
            String bankCode, String bankName, String bankCardNo,
            String subbranch, String bindMobile) {
        BankCard data = new BankCard();
        data.setOwnerId(ownerId);
        data.setOwnerName(ownerName);
        data.setType(type);
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
    public void refreshBankCard(Long id, String bankCode, String bankName,
            String bankCardNo, String subbranch, String bindMobile) {
        BankCard data = new BankCard();
        data.setId(id);
        data.setBankCode(bankCode);
        data.setBankName(bankName);
        data.setBankCardNo(bankCardNo);

        data.setSubbranch(subbranch);
        data.setBindMobile(bindMobile);
        data.setStatus(EBankCardStatus.TOCONFIRM.getCode());
        data.setUpdateDatetime(new Date());
        bankCardDAO.update(data);

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

    @Override
    public boolean isBankCardExist(Long id) {
        BankCard data = new BankCard();
        data.setId(id);
        if (bankCardDAO.selectTotalCount(data) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public void removeBankCard(Long id) {
        if (id > 0) {
            BankCard data = new BankCard();
            data.setId(id);
            bankCardDAO.delete(data);
        }
    }

    @Override
    public BankCard getBankCard(Long id) {
        BankCard bankCard = null;
        if (id != null) {
            BankCard condition = new BankCard();
            condition.setId(id);
            bankCard = bankCardDAO.select(condition);
        }
        return bankCard;
    }

    @Override
    public List<BankCard> queryBankCardList(String ownerId, EBankCardType type) {
        List<BankCard> list = null;
        if (StringUtils.isNotBlank(ownerId)) {
            BankCard condition = new BankCard();
            condition.setOwnerId(ownerId);
            condition.setType(type.getCode());
            list = bankCardDAO.selectList(condition);
        }
        return list;
    }

    /** 
     * @see com.std.account.bo.IBankCardBO#doKYC(java.lang.String,java.lang.String)
     */
    @Override
    public void doKYC(String companyId, String kycResult) {
        BankCard condition = new BankCard();
        condition.setOwnerId(companyId);
        List<BankCard> list = bankCardDAO.selectList(condition);
        for (BankCard bankCard : list) {
            BankCard data = new BankCard();
            data.setId(bankCard.getId());
            if (EBoolean.YES.getCode().equalsIgnoreCase(kycResult)) {
                data.setStatus(EBankCardStatus.CONFIRM_YES.getCode());
            } else {
                data.setStatus(EBankCardStatus.CONFIRM_NO.getCode());
            }
            bankCardDAO.updateStatus(data);
        }
    }
}
