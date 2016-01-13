package com.std.account.api.impl;

import com.std.account.api.AProcessor;
import com.std.account.api.converter.ArticleConverter;
import com.std.account.cms.ao.IArticleAO;
import com.std.account.cms.domain.Article;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN7020106Req;
import com.std.account.dto.res.XN7020106Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 修改文章
 * @author: xieyj 
 * @since: 2016年1月5日 下午1:40:16 
 * @history:
 */
public class XN7020106 extends AProcessor {
    private IArticleAO articleAO = SpringContextHolder
        .getBean(IArticleAO.class);

    private XN7020106Req xn7020106Req = null;

    @Override
    public Object doBusiness() throws BizException {
        Article data = new Article();
        data = ArticleConverter.convert(xn7020106Req);
        boolean result = articleAO.editArticle(data);
        return new XN7020106Res(result);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn7020106Req = JsonUtil.json2Bean(inputparams, XN7020106Req.class);
        StringValidater.validateBlank(xn7020106Req.getCode(),
            xn7020106Req.getTitle(), xn7020106Req.getContent(),
            xn7020106Req.getParentCode(), xn7020106Req.getOrderNo(),
            xn7020106Req.getUpdater());
    }
}
