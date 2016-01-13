package com.std.account.api.impl;

import com.std.account.api.AProcessor;
import com.std.account.api.converter.NavigationConverter;
import com.std.account.cms.ao.INavigationAO;
import com.std.account.cms.domain.Navigation;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN7020103Req;
import com.std.account.dto.res.XN7020103Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 修改导航
 * @author: xieyj 
 * @since: 2016年1月5日 下午2:09:21 
 * @history:
 */
public class XN7020103 extends AProcessor {
    private INavigationAO navigationAO = SpringContextHolder
        .getBean(INavigationAO.class);

    private XN7020103Req xn7020103Req = null;

    @Override
    public Object doBusiness() throws BizException {
        Navigation navigation = new Navigation();
        navigation = NavigationConverter.convert(xn7020103Req);
        boolean result = navigationAO.editNavigation(navigation);
        return new XN7020103Res(result);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn7020103Req = JsonUtil.json2Bean(inputparams, XN7020103Req.class);
        StringValidater.validateBlank(xn7020103Req.getCode(),
            xn7020103Req.getTitle(), xn7020103Req.getType(),
            xn7020103Req.getParentCode(), xn7020103Req.getOrderNo(),
            xn7020103Req.getUpdater());
    }
}
