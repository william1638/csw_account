package com.std.account.dao;

import com.std.account.dao.base.IBaseDAO;
import com.std.account.domain.BankCard;

/**
 * 
 * @author: asus 
 * @since: 2016年12月22日 下午4:31:08 
 * @history:
 */
public interface IBankCardDAO extends IBaseDAO<BankCard> {
    String NAMESPACE = IBankCardDAO.class.getName().concat(".");

    public int update(BankCard data);
}
