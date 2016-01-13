package com.std.account.api.converter;

import org.apache.commons.lang3.StringUtils;

import com.std.account.cms.domain.Article;
import com.std.account.common.DateUtil;
import com.std.account.dto.req.XN7020104Req;
import com.std.account.dto.req.XN7020106Req;
import com.std.account.dto.req.XN7020203Req;
import com.std.account.dto.req.XN7020204Req;

public class ArticleConverter {
    /*
     * 新增文章
     */
    public static Article convert(XN7020104Req xn7020004Req) {
        Article article = new Article();
        article.setCode(xn7020004Req.getCode());
        article.setTitle(xn7020004Req.getTitle());
        article.setContent(xn7020004Req.getContent());
        article.setParentCode(xn7020004Req.getParentCode());
        article.setArtUrl(xn7020004Req.getArtUrl());
        article.setOrderNo(xn7020004Req.getOrderNo());
        article.setCreator(xn7020004Req.getCreator());
        article.setCreateDatetime(xn7020004Req.getCreateDatetime());
        return article;
    }

    /*
     * 修改文章
     */
    public static Article convert(XN7020106Req xn7020006Req) {
        Article article = new Article();
        article.setCode(xn7020006Req.getCode());
        article.setTitle(xn7020006Req.getTitle());
        article.setContent(xn7020006Req.getContent());
        article.setParentCode(xn7020006Req.getParentCode());
        article.setArtUrl(xn7020006Req.getArtUrl());
        article.setOrderNo(xn7020006Req.getOrderNo());
        article.setUpdater(xn7020006Req.getUpdater());
        article.setUpdateDatetime(xn7020006Req.getUpdateDatetime());
        return article;
    }

    /*
     * 分页查询
     */
    public static Article convert(XN7020203Req xn7020151Req) {
        Article article = new Article();
        article.setCode(xn7020151Req.getCode());
        article.setTitle(xn7020151Req.getTitle());
        article.setParentCode(xn7020151Req.getParentCode());
        article.setStatus(xn7020151Req.getStatus());
        article.setCreateDatetimeStart(DateUtil.getFrontDate(
            xn7020151Req.getDateStart(), false));
        article.setCreateDatetimeEnd(DateUtil.getFrontDate(
            xn7020151Req.getDateEnd(), true));
        String column = xn7020151Req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = "code";
        }
        article.setOrder(column, xn7020151Req.getOrderDir());
        return article;
    }

    /*
     * 列表查询
     */
    public static Article convert(XN7020204Req xn7020152Req) {
        Article article = new Article();
        article.setCode(xn7020152Req.getCode());
        article.setTitle(xn7020152Req.getTitle());
        article.setParentCode(xn7020152Req.getParentCode());
        article.setStatus(xn7020152Req.getStatus());
        return article;
    }
}
