package com.std.account.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.account.ao.IAccountAO;
import com.std.account.ao.IExchangeCurrencyAO;
import com.std.account.bo.IExchangeCurrencyBO;
import com.std.account.bo.IUserBO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.ExchangeCurrency;
import com.std.account.domain.User;
import com.std.account.enums.EBizType;
import com.std.account.enums.EBoolean;
import com.std.account.enums.EExchangeCurrencyStatus;
import com.std.account.exception.BizException;

@Service
public class ExchangeCurrencyAOImpl implements IExchangeCurrencyAO {

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IAccountAO accountAO;

    @Autowired
    private IExchangeCurrencyBO exchangeCurrencyBO;

    @Override
    public Object payExchange(String userId, Long amount, String currency,
            String payType) {
        User user = userBO.getRemoteUser(userId);
        return exchangeCurrencyBO.payExchange(user, amount, currency, payType);
    }

    @Override
    @Transactional
    public void paySuccess(String payGroup, String payCode, Long transAmount) {
        List<ExchangeCurrency> resultList = exchangeCurrencyBO
            .queryExchangeCurrencyList(payGroup);
        if (CollectionUtils.isEmpty(resultList)) {
            throw new BizException("XN000000", "找不到对应的兑换记录");
        }
        ExchangeCurrency exchangeCurrency = resultList.get(0);
        if (!transAmount.equals(exchangeCurrency.getFromAmount())) {
            throw new BizException("XN000000", "金额校验错误，非正常调用");
        }
        // 更新状态
        exchangeCurrencyBO.paySuccess(exchangeCurrency.getCode(),
            EExchangeCurrencyStatus.PAYED.getCode(), payCode, transAmount);
        // 双方划转
        accountAO.transAmountCZB(exchangeCurrency.getFromUserId(),
            exchangeCurrency.getToUserId(), exchangeCurrency.getFromCurrency(),
            transAmount, EBizType.EXCHANGE_CURRENCY.getCode(), "币种兑换", "币种兑换");
        accountAO.transAmountCZB(exchangeCurrency.getToUserId(),
            exchangeCurrency.getFromUserId(), exchangeCurrency.getToCurrency(),
            transAmount, EBizType.EXCHANGE_CURRENCY.getCode(), "币种兑换", "币种兑换");
    }

    @Override
    public Paginable<ExchangeCurrency> queryExchangeCurrencyPage(int start,
            int limit, ExchangeCurrency condition) {
        return exchangeCurrencyBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<ExchangeCurrency> queryExchangeCurrencyList(
            ExchangeCurrency condition) {
        return exchangeCurrencyBO.queryExchangeCurrencyList(condition);
    }

    @Override
    public ExchangeCurrency getExchangeCurrency(String code) {
        return exchangeCurrencyBO.getExchangeCurrency(code);
    }

    @Override
    public Double getExchangeRate(String currency) {
        return exchangeCurrencyBO.getExchangeRate(currency);
    }

    @Override
    public String applyExchange(String userId, Long amount, String currency) {
        User user = userBO.getRemoteUser(userId);
        return exchangeCurrencyBO.applyExchange(user, amount, currency);
    }

    @Override
    public void approveExchange(String code, String approveResult,
            String approver, String approveNote) {
        ExchangeCurrency dbOrder = exchangeCurrencyBO.getExchangeCurrency(code);
        if (EExchangeCurrencyStatus.TO_PAY.getCode()
            .equals(dbOrder.getStatus())) {
            if (EBoolean.YES.equals(approveResult)) {
                exchangeCurrencyBO.approveExchangeYes(dbOrder, approver,
                    approveNote);
            } else {
                exchangeCurrencyBO.approveExchangeNo(dbOrder, approver,
                    approveNote);
            }
        } else {
            throw new BizException("xn000000", code + "不处于待审批状态");
        }
    }

}
