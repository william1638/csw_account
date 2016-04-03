/**
 * @Title IBankcardBO.java 
 * @Package com.ibis.pz.user 
 * @Description 
 * @author luoqi  
 * @date 2015年3月8日 上午11:45:48 
 * @version V1.0   
 */
package com.std.account.bo;

import java.util.List;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.BankCard;
import com.std.account.enums.EBankCardStatus;
import com.std.account.enums.EBankCardType;

/** 
 * @author: luoqi 
 * @since: 2015年3月8日 上午11:45:48 
 * @history:
 */
public interface IBankCardBO extends IPaginableBO<BankCard> {
    public boolean isBankCardExist(Long id);

    public void saveBankCard(String ownerId, String ownerName, String type,
            String bankCode, String bankName, String bankCardNo,
            String subbranch, String bindMobile);

    public void refreshBankCard(Long id, String bankCode, String bankName,
            String bankCardNo, String subbranch, String bindMobile);

    public void refreshStatus(String bankCode, String bankcardNo,
            EBankCardStatus status);

    /**
     * kyc企业银行账户
     * @param companyId
     * @param kycResult 
     * @create: 2016年4月3日 下午4:40:01 xieyj
     * @history:
     */
    public void doKYC(String companyId, String kycResult);

    public void removeBankCard(Long id);

    public BankCard getBankCard(Long id);

    public List<BankCard> queryBankCardList(String ownerId, EBankCardType type);

}
