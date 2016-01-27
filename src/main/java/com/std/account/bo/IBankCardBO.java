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

    public void saveBankCard(String userId, String type, String bankCode,
            String bankName, String bankCardNo, String subbranch,
            String bindMobile);

    public void refreshBankCard(Long id, String userId, String bankCode,
            String bankName, String bankCardNo, String subbranch,
            String bindMobile);

    public void refreshStatus(String bankCode, String bankcardNo,
            EBankCardStatus status);

    public void removeBankCard(Long id);

    public List<BankCard> queryBankCardList(String userId, EBankCardType type);

}
