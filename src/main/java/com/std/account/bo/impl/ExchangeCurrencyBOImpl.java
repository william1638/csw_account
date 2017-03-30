package com.std.account.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.IExchangeCurrencyBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.dao.IExchangeCurrencyDAO;
import com.std.account.domain.ExchangeCurrency;
import com.std.account.exception.BizException;

@Component
public class ExchangeCurrencyBOImpl extends PaginableBOImpl<ExchangeCurrency>
        implements IExchangeCurrencyBO {

    @Autowired
    private IExchangeCurrencyDAO exchangeCurrencyDAO;

    @Override
    public void saveExchangeCurrency(ExchangeCurrency data) {
        if (data != null && StringUtils.isNotBlank(data.getCode())) {
            exchangeCurrencyDAO.insert(data);
        }
    }

    @Override
    public List<ExchangeCurrency> queryExchangeCurrencyList(
            ExchangeCurrency condition) {
        return exchangeCurrencyDAO.selectList(condition);
    }

    public List<ExchangeCurrency> queryExchangeCurrencyList(String payGroup) {
        ExchangeCurrency condition = new ExchangeCurrency();
        condition.setPayGroup(payGroup);
        return exchangeCurrencyDAO.selectList(condition);
    }

    @Override
    public ExchangeCurrency getExchangeCurrency(String code) {
        ExchangeCurrency data = null;
        if (StringUtils.isNotBlank(code)) {
            ExchangeCurrency condition = new ExchangeCurrency();
            condition.setCode(code);
            data = exchangeCurrencyDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "记录不存在");
            }
        }
        return data;
    }

    /** 
     * @see com.std.account.bo.IExchangeCurrencyBO#refreshPayStatus(java.lang.String, java.lang.String, java.lang.String, java.lang.Long)
     */
    @Override
    public int refreshPayStatus(String code, String status, String payCode,
            Long payAmount) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            ExchangeCurrency data = new ExchangeCurrency();
            data.setCode(code);
            data.setStatus(status);
            data.setPayCode(payCode);
            data.setPayAmount(payAmount);
            count = exchangeCurrencyDAO.updatePayStatus(data);
        }
        return count;

    }
}
