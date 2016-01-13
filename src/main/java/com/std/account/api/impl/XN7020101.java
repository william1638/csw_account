package com.std.account.api.impl;

import com.std.account.api.AProcessor;
import com.std.account.api.converter.NavigationConverter;
import com.std.account.cms.ao.INavigationAO;
import com.std.account.cms.domain.Navigation;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN7020101Req;
import com.std.account.dto.res.XN7020101Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 新增导航
 * @author: xieyj 
 * @since: 2016年1月5日 下午2:07:50 
 * @history:
 */
public class XN7020101 extends AProcessor {
    private INavigationAO navigationAO = SpringContextHolder
        .getBean(INavigationAO.class);

    private XN7020101Req xn7020101Req = null;

    @Override
    public Object doBusiness() throws BizException {
        Navigation navigation = new Navigation();
        navigation = NavigationConverter.convert(xn7020101Req);
        boolean result = navigationAO.addNavigation(navigation);
        return new XN7020101Res(result);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn7020101Req = JsonUtil.json2Bean(inputparams, XN7020101Req.class);
        StringValidater.validateBlank(xn7020101Req.getCode(),
            xn7020101Req.getTitle(), xn7020101Req.getType(),
            xn7020101Req.getParentCode(), xn7020101Req.getOrderNo(),
            xn7020101Req.getCreator());
    }
}
