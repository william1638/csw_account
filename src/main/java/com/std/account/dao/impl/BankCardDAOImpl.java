package com.std.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.account.dao.IBankCardDAO;
import com.std.account.dao.base.support.AMybatisTemplate;
import com.std.account.domain.BankCard;

/**
 * 
 * @author: asus 
 * @since: 2016年12月22日 下午4:30:48 
 * @history:
 */
@Repository("bankcardDAOImpl")
public class BankCardDAOImpl extends AMybatisTemplate implements IBankCardDAO {

    @Override
    public int insert(BankCard data) {
        return super.insert(NAMESPACE.concat("insert_bankcard"), data);
    }

    @Override
    public int delete(BankCard data) {
        return super.delete(NAMESPACE.concat("delete_bankcard"), data);
    }

    @Override
    public BankCard select(BankCard condition) {
        return super.select(NAMESPACE.concat("select_bankcard"), condition,
            BankCard.class);
    }

    @Override
    public long selectTotalCount(BankCard condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_bankcard_count"), condition);
    }

    @Override
    public List<BankCard> selectList(BankCard condition) {
        return super.selectList(NAMESPACE.concat("select_bankcard"), condition,
            BankCard.class);
    }

    @Override
    public List<BankCard> selectList(BankCard condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_bankcard"), start,
            count, condition, BankCard.class);
    }

    @Override
    public int update(BankCard data) {
        return super.update(NAMESPACE.concat("update_bankcard"), data);
    }

}
