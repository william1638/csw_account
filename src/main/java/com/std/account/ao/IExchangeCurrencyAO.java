package com.std.account.ao;

import java.util.List;

import com.std.account.bo.base.Paginable;
import com.std.account.domain.ExchangeCurrency;

public interface IExchangeCurrencyAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public Object payExchangeCurrency(ExchangeCurrency data);

    public void paySuccess(String payGroup, String payCode, Long transAmount);

    public Paginable<ExchangeCurrency> queryExchangeCurrencyPage(int start,
            int limit, ExchangeCurrency condition);

    public List<ExchangeCurrency> queryExchangeCurrencyList(
            ExchangeCurrency condition);

    public ExchangeCurrency getExchangeCurrency(String code);

}
