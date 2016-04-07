/**
 * @Title BankCardAOImpl.java 
 * @Package com.ibis.pz.user.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-8 上午10:52:06 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.account.ao.IBankCardAO;
import com.std.account.bo.IBankCardBO;
import com.std.account.bo.ICompanyBO;
import com.std.account.bo.IUserBO;
import com.std.account.bo.IUserCompanyBO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.BankCard;
import com.std.account.domain.Company;
import com.std.account.domain.User;
import com.std.account.enums.EBankCardType;
import com.std.account.enums.ECompanyStatus;
import com.std.account.exception.BizException;

/** 
 * @author: miyb 
 * @since: 2015-3-8 上午10:52:06 
 * @history:
 */
@Service
public class BankCardAOImpl implements IBankCardAO {
    @Autowired
    IUserBO userBO;

    @Autowired
    IBankCardBO bankCardBO;

    @Autowired
    ICompanyBO companyBO;

    @Autowired
    IUserCompanyBO userCompanyBO;

    @Override
    public List<BankCard> queryBankCardList(String userId, EBankCardType type) {
        return bankCardBO.queryBankCardList(userId, type);
    }

    /** 
     * @see com.std.account.ao.IBankCardAO#getBankCard(java.lang.Long)
     */
    @Override
    public BankCard getBankCard(Long id) {
        return bankCardBO.getBankCard(id);
    }

    @Override
    @Transactional
    public void doBindBandCard(String ownerId, String type, String bankCode,
            String bankName, String bankCardNo, String subbranch,
            String bindMobile) {
        if (EBankCardType.User.getCode().equalsIgnoreCase(type)) {
            User user = userBO.getUser(ownerId);
            bankCardBO.saveBankCard(ownerId, user.getRealName(), type,
                bankCode, bankName, bankCardNo, subbranch, bindMobile);
        } else {
            Company company = companyBO.getCompany(ownerId);
            bankCardBO.saveBankCard(ownerId, company.getCompanyName(), type,
                bankCode, bankName, bankCardNo, subbranch, bindMobile);
        }
    }

    @Override
    public Paginable<BankCard> queryBankCardPage(int start, int limit,
            BankCard condition) {
        return bankCardBO.getPaginable(start, limit, condition);
    }

    @Override
    public void dropBankCard(Long id) {
        if (!bankCardBO.isBankCardExist((id))) {
            throw new BizException("xn702000", "删除银行卡不存在！");
        }
        bankCardBO.removeBankCard(id);

    }

    @Override
    public void doRebindBankCard(Long id, String userId, String bankCode,
            String bankName, String bankCardNo, String subbranch,
            String bindMobile) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("xn702000", "用户不存在");
        }
        if (StringUtils.isBlank(user.getRealName())) {
            throw new BizException("xn702000", "请先实名认证");
        }
        BankCard bankCard = bankCardBO.getBankCard(id);
        if (bankCard == null) {
            throw new BizException("xn702000", "编号不存在");
        }
        if (EBankCardType.User.getCode().equalsIgnoreCase(bankCard.getType())) {
            // 功能暂未实现
        }
        bankCardBO.refreshBankCard(id, bankCode, bankName, bankCardNo,
            subbranch, bindMobile);
    }

    @Override
    public List<BankCard> queryAllBankCardList(String userId) {
        List<BankCard> returnList = new ArrayList<BankCard>();
        returnList.addAll(bankCardBO.queryBankCardList(userId,
            EBankCardType.User));
        List<Company> companyList = userCompanyBO.queryCompanyList(userId,
            ECompanyStatus.KYC_YES.getCode());
        if (CollectionUtils.isNotEmpty(companyList)) {
            for (Company company : companyList) {
                returnList.addAll(bankCardBO.queryBankCardList(
                    company.getCompanyId(), EBankCardType.Company));
            }
        }
        return returnList;
    }
}
