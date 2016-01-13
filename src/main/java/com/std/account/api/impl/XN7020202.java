package com.std.account.api.impl;

import com.std.account.api.AProcessor;
import com.std.account.api.converter.NavigationConverter;
import com.std.account.cms.ao.INavigationAO;
import com.std.account.cms.domain.Navigation;
import com.std.account.common.JsonUtil;
import com.std.account.dto.req.XN7020202Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 导航列表查询
 * @author: xieyj 
 * @since: 2016年1月6日 上午11:38:00 
 * @history:
 */
public class XN7020202 extends AProcessor {
    private INavigationAO navigationAO = SpringContextHolder
        .getBean(INavigationAO.class);

    private XN7020202Req xn7020202Req = null;

    @Override
    public Object doBusiness() throws BizException {
        Navigation data = new Navigation();
        data = NavigationConverter.convert(xn7020202Req);
        return navigationAO.queryNavigationList(data);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn7020202Req = JsonUtil.json2Bean(inputparams, XN7020202Req.class);
    }
}
