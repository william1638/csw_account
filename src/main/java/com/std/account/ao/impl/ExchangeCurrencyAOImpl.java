package com.std.account.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.account.ao.IAccountAO;
import com.std.account.ao.IExchangeCurrencyAO;
import com.std.account.bo.IExchangeCurrencyBO;
import com.std.account.bo.base.Paginable;
import com.std.account.core.OrderNoGenerater;
import com.std.account.core.StringValidater;
import com.std.account.domain.ExchangeCurrency;
import com.std.account.enums.EBizType;
import com.std.account.enums.EExchangeCurrencyStatus;
import com.std.account.enums.EGeneratePrefix;
import com.std.account.enums.EPayType;
import com.std.account.exception.BizException;

@Service
public class ExchangeCurrencyAOImpl implements IExchangeCurrencyAO {

    @Autowired
    private IAccountAO accountAO;

    @Autowired
    private IExchangeCurrencyBO exchangeCurrencyBO;

    @Override
    public Object payExchangeCurrency(ExchangeCurrency req) {
        Object result = null;
        if (EPayType.WEIXIN.getCode().equals(req.getPayType())) {
            doSaveExchangeCurrency(req);
        }
        return result;
    }

    /** 
     * @param req
     * @return 
     * @create: 2017年3月30日 下午1:25:09 xieyj
     * @history: 
     */
    private String doSaveExchangeCurrency(ExchangeCurrency req) {
        ExchangeCurrency data = new ExchangeCurrency();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.EXCHANGE_CURRENCY.getCode());
        data.setCode(code);
        data.setToUserId(req.getToUserId());
        data.setToAmount(StringValidater.toLong(req.getToAmount()));
        data.setToCurrency(req.getToCurrency());
        data.setFromUserId(req.getFromUserId());
        data.setFromAmount(StringValidater.toLong(req.getFromAmount()));
        data.setFromCurrency(req.getFromCurrency());
        data.setCreateDatetime(new Date());
        data.setStatus(EExchangeCurrencyStatus.TO_PAY.getCode());
        data.setPayType(EPayType.WEIXIN.getCode());
        data.setPayGroup(code);
        data.setSystemCode(req.getSystemCode());
        data.setCompanyCode(req.getCompanyCode());
        return code;
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
        exchangeCurrencyBO.refreshPayStatus(exchangeCurrency.getCode(),
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
}
