package com.std.account.api.impl;

import com.std.account.api.AProcessor;
import com.std.account.cms.ao.IArticleAO;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN7020053Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 获取文章详情
 * @author: xieyj 
 * @since: 2016年1月6日 上午9:14:16 
 * @history:
 */
public class XN7020053 extends AProcessor {
    private IArticleAO articleAO = SpringContextHolder
        .getBean(IArticleAO.class);

    private XN7020053Req xn7020053Req = null;

    @Override
    public Object doBusiness() throws BizException {
        return articleAO.getArticle(xn7020053Req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn7020053Req = JsonUtil.json2Bean(inputparams, XN7020053Req.class);
        StringValidater.validateBlank(xn7020053Req.getCode());
    }
}
