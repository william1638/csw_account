package com.std.account.api.impl;

import com.std.account.api.AProcessor;
import com.std.account.cms.ao.IArticleAO;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN7020108Req;
import com.std.account.dto.res.XN7020108Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/** 
 * 更改文章状态
 * @author: xieyj 
 * @since: 2016年1月5日 下午5:34:22 
 * @history:
 */
public class XN7020108 extends AProcessor {
    private IArticleAO articleAO = SpringContextHolder
        .getBean(IArticleAO.class);

    private XN7020108Req xn7020108Req = null;

    @Override
    public Object doBusiness() throws BizException {
        boolean result = articleAO.changeArticleStatus(xn7020108Req.getCode(),
            xn7020108Req.getStatus(), xn7020108Req.getUpdater());
        return new XN7020108Res(result);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn7020108Req = JsonUtil.json2Bean(inputparams, XN7020108Req.class);
        StringValidater.validateBlank(xn7020108Req.getCode(),
            xn7020108Req.getStatus(), xn7020108Req.getUpdater());
    }
}
