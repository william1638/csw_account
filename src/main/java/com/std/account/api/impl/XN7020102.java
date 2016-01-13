package com.std.account.api.impl;

import org.apache.commons.lang.StringUtils;

import com.std.account.api.AProcessor;
import com.std.account.cms.ao.INavigationAO;
import com.std.account.common.JsonUtil;
import com.std.account.dto.req.XN7020102Req;
import com.std.account.dto.res.XN7020102Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 删除导航
 * @author: xieyj 
 * @since: 2016年1月5日 下午2:08:42 
 * @history:
 */
public class XN7020102 extends AProcessor {
    private INavigationAO navigationAO = SpringContextHolder
        .getBean(INavigationAO.class);

    private XN7020102Req xn7020102Req = null;

    @Override
    public Object doBusiness() throws BizException {
        boolean result = navigationAO.dropNavigation(xn7020102Req.getCode());
        return new XN7020102Res(result);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn7020102Req = JsonUtil.json2Bean(inputparams, XN7020102Req.class);
        StringUtils.isNotBlank(xn7020102Req.getCode());
    }
}
