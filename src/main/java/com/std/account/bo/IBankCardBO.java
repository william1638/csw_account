/**
 * @Title IBankcardBO.java 
 * @Package com.ibis.pz.user 
 * @Description 
 * @author luoqi  
 * @date 2015年3月8日 上午11:45:48 
 * @version V1.0   
 */
package com.std.account.bo;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.BankCard;
import com.std.account.enums.EBankCardStatus;

/** 
 * @author: luoqi 
 * @since: 2015年3月8日 上午11:45:48 
 * @history:
 */
public interface IBankCardBO extends IPaginableBO<BankCard> {

    public void saveBankCard(String userId, String bankCode, String bankName,
            String bankCardNo, String subbranch, String bindMobile);

    public void refreshBankCard(BankCard dbBankCard, String bankCode,
            String bankName, String bankCardNo, String subbranch,
            String bindMobile);

    public BankCard getBankCard(String userId);

    public void refreshStatus(String bankCode, String bankcardNo,
            EBankCardStatus status);

}
