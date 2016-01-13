package com.std.account.api.impl;

import com.std.account.api.AProcessor;
import com.std.account.api.converter.ArticleConverter;
import com.std.account.cms.ao.IArticleAO;
import com.std.account.cms.domain.Article;
import com.std.account.common.JsonUtil;
import com.std.account.dto.req.XN7020204Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 文章列表查询
 * @author: xieyj 
 * @since: 2016年1月5日 下午1:47:27 
 * @history:
 */
public class XN7020204 extends AProcessor {
    private IArticleAO articleAO = SpringContextHolder
        .getBean(IArticleAO.class);

    private XN7020204Req xn7020204Req = null;

    @Override
    public Object doBusiness() throws BizException {
        Article data = new Article();
        data = ArticleConverter.convert(xn7020204Req);
        return articleAO.queryArticleList(data);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn7020204Req = JsonUtil.json2Bean(inputparams, XN7020204Req.class);
    }
}
