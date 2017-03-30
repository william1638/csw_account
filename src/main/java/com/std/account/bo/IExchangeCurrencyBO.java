package com.std.account.bo;

import java.util.List;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.ExchangeCurrency;

/**
 * @author: xieyj 
 * @since: 2017年3月30日 下午1:01:17 
 * @history:
 */
public interface IExchangeCurrencyBO extends IPaginableBO<ExchangeCurrency> {

    public void saveExchangeCurrency(ExchangeCurrency data);

    public int refreshPayStatus(String code, String status, String payCode,
            Long payAmount);

    public List<ExchangeCurrency> queryExchangeCurrencyList(
            ExchangeCurrency condition);

    public List<ExchangeCurrency> queryExchangeCurrencyList(String payGroup);

    public ExchangeCurrency getExchangeCurrency(String code);

}
