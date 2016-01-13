package com.std.account.dao;

import com.std.account.dao.base.IBaseDAO;
import com.std.account.domain.BankCard;

/**
 * @author: xieyj 
 * @since: 2015-3-7 下午2:01:11 
 * @history:
 */
public interface IBankCardDAO extends IBaseDAO<BankCard> {
    String NAMESPACE = IBankCardDAO.class.getName().concat(".");

    /**
     * 更改卡的状态
     */
    public int updateStatus(BankCard data);

    /**
     * 更新银行卡
     */
    public int update(BankCard data);
}
