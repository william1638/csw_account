/**
 * @Title IBankBO.java 
 * @Package com.ibis.account.bo 
 * @Description 
 * @author miyb  
 * @date 2015-6-16 下午4:52:01 
 * @version V1.0   
 */
package com.std.account.bo;

import java.util.List;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.Bank;

/** 
 * @author: miyb 
 * @since: 2015-6-16 下午4:52:01 
 * @history:
 */
public interface IBankBO extends IPaginableBO<Bank> {
    public boolean isBankExist(Long id);

    public int saveBank(Bank data);

    public int removeBank(Long id);

    public int refreshBank(Bank data);

    public Bank getBank(Long id);

    public List<Bank> queryBankList(Bank data);
}
