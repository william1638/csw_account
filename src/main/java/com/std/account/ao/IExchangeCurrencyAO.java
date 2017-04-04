package com.std.account.ao;

import java.util.List;

import com.std.account.bo.base.Paginable;
import com.std.account.domain.ExchangeCurrency;

public interface IExchangeCurrencyAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 获取虚拟币价值。1人民币等于多少虚拟币
    public Double getExchangeRate(String currency);

    public String applyExchange(String userId, Long amount, String currency);

    public void approveExchange(String code, String approveResult,
            String approver, String approveNote);

    public Object payExchange(String userId, Long amount, String currency,
            String payType);

    public void paySuccess(String payGroup, String payCode, Long transAmount);

    public Paginable<ExchangeCurrency> queryExchangeCurrencyPage(int start,
            int limit, ExchangeCurrency condition);

    public List<ExchangeCurrency> queryExchangeCurrencyList(
            ExchangeCurrency condition);

    public ExchangeCurrency getExchangeCurrency(String code);

}
