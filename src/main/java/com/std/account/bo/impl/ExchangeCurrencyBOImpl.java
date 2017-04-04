package com.std.account.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.IExchangeCurrencyBO;
import com.std.account.bo.ISYSConfigBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.core.OrderNoGenerater;
import com.std.account.dao.IExchangeCurrencyDAO;
import com.std.account.domain.ExchangeCurrency;
import com.std.account.domain.User;
import com.std.account.enums.ECurrency;
import com.std.account.enums.EExchangeCurrencyStatus;
import com.std.account.enums.EGeneratePrefix;
import com.std.account.enums.EPayType;
import com.std.account.exception.BizException;

@Component
public class ExchangeCurrencyBOImpl extends PaginableBOImpl<ExchangeCurrency>
        implements IExchangeCurrencyBO {
    @Autowired
    ISYSConfigBO sysConfigBO;

    @Autowired
    private IExchangeCurrencyDAO exchangeCurrencyDAO;

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

    @Override
    public int paySuccess(String code, String status, String payCode,
            Long payAmount) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            ExchangeCurrency data = new ExchangeCurrency();
            data.setCode(code);
            data.setStatus(status);
            data.setPayCode(payCode);
            data.setPayAmount(payAmount);
            data.setPayDatetime(new Date());
            count = exchangeCurrencyDAO.paySuccess(data);
        }
        return count;

    }

    @Override
    public Double getExchangeRate(String fromCurrency, String toCurrency) {
        if (ECurrency.CNY.getCode().equalsIgnoreCase(fromCurrency)
                && ECurrency.GXZ.getCode().equalsIgnoreCase(toCurrency)) {
            return sysConfigBO.getCNY2GXZ();
        } else if (ECurrency.CNY.getCode().equalsIgnoreCase(fromCurrency)
                && ECurrency.FRB.getCode().equalsIgnoreCase(toCurrency)) {
            return sysConfigBO.getCNY2FRB();
        } else if (ECurrency.CNY.getCode().equalsIgnoreCase(fromCurrency)
                && ECurrency.CGB.getCode().equalsIgnoreCase(toCurrency)) {
            return sysConfigBO.getCNY2CGB();
        } else if (ECurrency.CNY.getCode().equalsIgnoreCase(fromCurrency)
                && ECurrency.CGJF.getCode().equalsIgnoreCase(toCurrency)) {
            Double a = sysConfigBO.getCNY2CGB();
            Double b = sysConfigBO.getCGB2CGJF();
            return a * b;
        } else if (ECurrency.HBB.getCode().equalsIgnoreCase(fromCurrency)
                && ECurrency.GXZ.getCode().equalsIgnoreCase(toCurrency)) {
            return sysConfigBO.getHBB2GXZ();
        } else if (ECurrency.HBYJ.getCode().equalsIgnoreCase(fromCurrency)
                && ECurrency.GXZ.getCode().equalsIgnoreCase(toCurrency)) {
            return sysConfigBO.getHBYJ2GXZ();
        } else if (ECurrency.CGB.getCode().equalsIgnoreCase(fromCurrency)
                && ECurrency.CGJF.getCode().equalsIgnoreCase(toCurrency)) {
            return sysConfigBO.getCGB2CGJF();
        } else {
            throw new BizException("xn000000", "兑换比例不存在，请检查钱包汇率规则参数");
        }
    }

    @Override
    public String applyExchange(User user, Long fromAmount,
            String fromCurrency, String toCurrency) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.EXCHANGE_CURRENCY.getCode());
        Double rate = this.getExchangeRate(fromCurrency, toCurrency);
        Long toAmount = Double.valueOf(fromAmount * rate).longValue();
        ExchangeCurrency data = new ExchangeCurrency();
        data.setCode(code);

        data.setToUserId(user.getUserId());
        data.setToAmount(toAmount);
        data.setToCurrency(toCurrency);
        data.setFromUserId(user.getUserId());
        data.setFromAmount(fromAmount);
        data.setFromCurrency(fromCurrency);

        data.setCreateDatetime(new Date());
        data.setStatus(EExchangeCurrencyStatus.TO_PAY.getCode());

        data.setSystemCode(user.getSystemCode());
        data.setCompanyCode(user.getCompanyCode());
        exchangeCurrencyDAO.applyExchange(data);
        return code;
    }

    @Override
    public void approveExchangeYes(ExchangeCurrency dbOrder, String approver,
            String approveNote) {
        dbOrder.setStatus(EExchangeCurrencyStatus.PAYED.getCode());
        dbOrder.setUpdater(approver);
        dbOrder.setUpdateDatetime(new Date());
        dbOrder.setRemark(approveNote);
        exchangeCurrencyDAO.approveExchange(dbOrder);
    }

    @Override
    public void approveExchangeNo(ExchangeCurrency dbOrder, String approver,
            String approveNote) {
        dbOrder.setStatus(EExchangeCurrencyStatus.CANCEL.getCode());
        dbOrder.setUpdater(approver);
        dbOrder.setUpdateDatetime(new Date());
        dbOrder.setRemark(approveNote);
        exchangeCurrencyDAO.approveExchange(dbOrder);
    }

    @Override
    public Object payExchange(User user, Long amount, String currency,
            String payType) {
        Object result = null;
        if (EPayType.WEIXIN.getCode().equals(payType)) {
            String code = OrderNoGenerater
                .generate(EGeneratePrefix.EXCHANGE_CURRENCY.getCode());
            Double rate = this.getExchangeRate(ECurrency.CNY.getCode(),
                currency);
            Long cny = Double.valueOf(amount / rate).longValue();
            String userId = user.getUserId();
            ExchangeCurrency data = new ExchangeCurrency();
            data.setCode(code);

            data.setToUserId(userId);
            data.setToAmount(amount);
            data.setToCurrency(currency);
            data.setFromUserId(userId);
            data.setFromAmount(cny);
            data.setFromCurrency(ECurrency.CNY.getCode());

            data.setCreateDatetime(new Date());
            data.setStatus(EExchangeCurrencyStatus.TO_PAY.getCode());

            data.setPayType(EPayType.WEIXIN.getCode());
            data.setPayGroup(code);

            data.setSystemCode(user.getSystemCode());
            data.setCompanyCode(user.getCompanyCode());
            exchangeCurrencyDAO.payExchange(data);

        }
        return result;
    }
}
