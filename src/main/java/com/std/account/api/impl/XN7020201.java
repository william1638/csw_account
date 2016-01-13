package com.std.account.api.impl;

import com.std.account.api.AProcessor;
import com.std.account.api.converter.NavigationConverter;
import com.std.account.cms.ao.INavigationAO;
import com.std.account.cms.domain.Navigation;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN7020201Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 导航分页查询
 * @author: xieyj 
 * @since: 2016年1月6日 上午11:38:00 
 * @history:
 */
public class XN7020201 extends AProcessor {
    private INavigationAO navigationAO = SpringContextHolder
        .getBean(INavigationAO.class);

    private XN7020201Req xn7020201Req = null;

    @Override
    public Object doBusiness() throws BizException {
        Navigation data = new Navigation();
        data = NavigationConverter.convert(xn7020201Req);
        return navigationAO.queryNavigationPage(
            Integer.valueOf(xn7020201Req.getStart()),
            Integer.valueOf(xn7020201Req.getLimit()), data);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn7020201Req = JsonUtil.json2Bean(inputparams, XN7020201Req.class);
        StringValidater.validateNumber(xn7020201Req.getStart(),
            xn7020201Req.getLimit());
    }
}
