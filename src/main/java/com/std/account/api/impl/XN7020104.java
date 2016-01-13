package com.std.account.api.impl;

import com.std.account.api.AProcessor;
import com.std.account.api.converter.ArticleConverter;
import com.std.account.cms.ao.IArticleAO;
import com.std.account.cms.domain.Article;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN7020104Req;
import com.std.account.dto.res.XN7020104Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 新增文章
 * @author: xieyj 
 * @since: 2016年1月5日 下午1:38:58 
 * @history:
 */
public class XN7020104 extends AProcessor {
    private IArticleAO articleAO = SpringContextHolder
        .getBean(IArticleAO.class);

    private XN7020104Req xn7020104Req = null;

    @Override
    public Object doBusiness() throws BizException {
        Article article = ArticleConverter.convert(xn7020104Req);
        boolean result = articleAO.addArticle(article);
        return new XN7020104Res(result);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn7020104Req = JsonUtil.json2Bean(inputparams, XN7020104Req.class);
        StringValidater.validateBlank(xn7020104Req.getCode(),
            xn7020104Req.getTitle(), xn7020104Req.getContent(),
            xn7020104Req.getParentCode(), xn7020104Req.getOrderNo(),
            xn7020104Req.getCreator());
    }
}
