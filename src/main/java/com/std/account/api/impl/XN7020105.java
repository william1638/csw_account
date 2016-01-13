package com.std.account.api.impl;

import com.std.account.api.AProcessor;
import com.std.account.cms.ao.IArticleAO;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN7020105Req;
import com.std.account.dto.res.XN7020105Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 删除文章
 * @author: xieyj 
 * @since: 2016年1月5日 下午1:39:36 
 * @history:
 */
public class XN7020105 extends AProcessor {
    private IArticleAO articleAO = SpringContextHolder
        .getBean(IArticleAO.class);

    private XN7020105Req xn7020104Req = null;

    @Override
    public Object doBusiness() throws BizException {
        boolean result = articleAO.dropArticle(xn7020104Req.getCode());
        return new XN7020105Res(result);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn7020104Req = JsonUtil.json2Bean(inputparams, XN7020105Req.class);
        StringValidater.validateBlank(xn7020104Req.getCode());
    }

}
