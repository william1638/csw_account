package com.std.account.cms.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.cms.bo.IArticleBO;
import com.std.account.cms.dao.IArticleDAO;
import com.std.account.cms.domain.Article;
import com.std.account.enums.EArticleStatus;

@Component
public class ArticleBOImpl extends PaginableBOImpl<Article> implements
        IArticleBO {
    @Autowired
    private IArticleDAO articleDAO;

    @Override
    public boolean isArticleExist(String code) {
        boolean flag = false;
        Article condition = new Article();
        condition.setCode(code);
        if (articleDAO.selectTotalCount(condition) > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public int saveArticle(Article data) {
        int count = 0;
        if (data != null) {
            data.setStatus(EArticleStatus.DRAFT.getCode());
            data.setCreateDatetime(new Date());
            count = articleDAO.insert(data);
        }
        return count;
    }

    @Override
    public int removeArticle(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Article data = new Article();
            data.setCode(code);
            count = articleDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshArticle(Article data) {
        data.setUpdateDatetime(new Date());
        return articleDAO.update(data);
    }

    @Override
    public int refreshArticleStatus(String code, String status, String updater) {
        Article data = new Article();
        data.setCode(code);
        data.setStatus(status);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        return articleDAO.updateStatus(data);
    }

    @Override
    public Article getArticle(String code) {
        Article data = new Article();
        data.setCode(code);
        return articleDAO.select(data);
    }

    @Override
    public List<Article> queryArticleList(Article data) {
        return articleDAO.selectList(data);
    }
}
