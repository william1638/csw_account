package com.std.account.cms.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.account.bo.base.Paginable;
import com.std.account.cms.ao.IArticleAO;
import com.std.account.cms.bo.IArticleBO;
import com.std.account.cms.domain.Article;
import com.std.account.enums.EArticleStatus;
import com.std.account.exception.BizException;

@Service
public class ArticleAOImpl implements IArticleAO {
    @Autowired
    IArticleBO articleBO;

    @Override
    public boolean addArticle(Article data) {
        if (articleBO.isArticleExist(data.getCode())) {
            throw new BizException("XN700001", "文章编号已存在");
        }
        articleBO.saveArticle(data);
        return true;
    }

    @Override
    public boolean dropArticle(String code) {
        if (!articleBO.isArticleExist(code)) {
            throw new BizException("XN700001", "文章编号不存在");
        }
        articleBO.removeArticle(code);
        return true;
    }

    @Override
    public boolean editArticle(Article data) {
        if (!articleBO.isArticleExist(data.getCode())) {
            throw new BizException("XN700001", "文章编号不存在");
        }
        articleBO.refreshArticle(data);
        return true;
    }

    /** 
     * @see com.std.account.cms.ao.IArticleAO#changeArticleStatus(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public boolean changeArticleStatus(String code, String status,
            String updater) {
        if (!articleBO.isArticleExist(code)) {
            throw new BizException("XN700001", "文章编号不存在");
        }
        if (!EArticleStatus.getArticleStatusMap().containsKey(status)) {
            throw new BizException("XN700001", "文章状态不在枚举中");
        }
        articleBO.refreshArticleStatus(code, status, updater);
        return true;
    }

    @Override
    public Article getArticle(String code) {
        if (!articleBO.isArticleExist(code)) {
            throw new BizException("XN700001", "文章编号不存在");
        }
        return articleBO.getArticle(code);
    }

    @Override
    public Paginable<Article> queryArticlePage(int start, int limit,
            Article condition) {
        return articleBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Article> queryArticleList(Article condition) {
        return articleBO.queryArticleList(condition);
    }
}
