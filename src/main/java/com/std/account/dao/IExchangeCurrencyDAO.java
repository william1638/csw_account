package com.std.account.dao;

import com.std.account.dao.base.IBaseDAO;
import com.std.account.domain.ExchangeCurrency;

public interface IExchangeCurrencyDAO extends IBaseDAO<ExchangeCurrency> {
    String NAMESPACE = IExchangeCurrencyDAO.class.getName().concat(".");

    /**
     * 更新支付状态
     * @param data
     * @return 
     * @create: 2017年3月30日 下午12:59:34 xieyj
     * @history:
     */
    int updatePayStatus(ExchangeCurrency data);
}
