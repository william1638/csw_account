package com.std.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.account.dao.IExchangeCurrencyDAO;
import com.std.account.dao.base.support.AMybatisTemplate;
import com.std.account.domain.ExchangeCurrency;

@Repository("exchangeCurrencyDAOImpl")
public class ExchangeCurrencyDAOImpl extends AMybatisTemplate implements
        IExchangeCurrencyDAO {

    @Override
    public int insert(ExchangeCurrency data) {
        return super.insert(NAMESPACE.concat("insert_exchangeCurrency"), data);
    }

    @Override
    public int delete(ExchangeCurrency data) {
        return 0;
    }

    @Override
    public ExchangeCurrency select(ExchangeCurrency condition) {
        return super.select(NAMESPACE.concat("select_exchangeCurrency"),
            condition, ExchangeCurrency.class);
    }

    @Override
    public long selectTotalCount(ExchangeCurrency condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_exchangeCurrency_count"), condition);
    }

    @Override
    public List<ExchangeCurrency> selectList(ExchangeCurrency condition) {
        return super.selectList(NAMESPACE.concat("select_exchangeCurrency"),
            condition, ExchangeCurrency.class);
    }

    @Override
    public List<ExchangeCurrency> selectList(ExchangeCurrency condition,
            int start, int count) {
        return super.selectList(NAMESPACE.concat("select_exchangeCurrency"),
            start, count, condition, ExchangeCurrency.class);
    }

    /** 
     * @see com.std.account.dao.IExchangeCurrencyDAO#updatePayStatus(com.std.account.domain.ExchangeCurrency)
     */
    @Override
    public int updatePayStatus(ExchangeCurrency data) {
        return super.update(NAMESPACE.concat("update_payStatus"), data);
    }
}
