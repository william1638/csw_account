package com.std.account.bo;

import java.util.List;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.BankCard;



//CHECK ��鲢��ע�� 
public interface IBankcardBO extends IPaginableBO<BankCard> {


	public boolean isBankcardExist(String code);


	public String saveBankcard(BankCard data);


	public int removeBankcard(String code);


	public int refreshBankcard(BankCard data);


	public List<BankCard> queryBankcardList(BankCard condition);


	public BankCard getBankcard(String code);


}