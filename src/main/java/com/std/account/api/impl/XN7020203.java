package com.std.account.api.impl;

import com.std.account.api.AProcessor;
import com.std.account.api.converter.ArticleConverter;
import com.std.account.cms.ao.IArticleAO;
import com.std.account.cms.domain.Article;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN7020203Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 文章分页查询
 * @author: xieyj 
 * @since: 2016年1月6日 上午11:38:00 
 * @history:
 */
public class XN7020203 extends AProcessor {
    private IArticleAO articleAO = SpringContextHolder
        .getBean(IArticleAO.class);

    private XN7020203Req xn7020203Req = null;

    @Override
    public Object doBusiness() throws BizException {
        Article data = new Article();
        data = ArticleConverter.convert(xn7020203Req);
        return articleAO.queryArticlePage(
            Integer.valueOf(xn7020203Req.getStart()),
            Integer.valueOf(xn7020203Req.getLimit()), data);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn7020203Req = JsonUtil.json2Bean(inputparams, XN7020203Req.class);
        StringValidater.validateNumber(xn7020203Req.getStart(),
            xn7020203Req.getLimit());
    }

}
