package com.std.account.cms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.account.cms.dao.IArticleDAO;
import com.std.account.cms.domain.Article;
import com.std.account.dao.base.support.AMybatisTemplate;

@Repository("articleDAOImpl")
public class ArticleDAOImpl extends AMybatisTemplate implements IArticleDAO {

    @Override
    public int insert(Article data) {
        return super.insert(NAMESPACE.concat("insert_article"), data);
    }

    @Override
    public int delete(Article data) {
        return super.delete(NAMESPACE.concat("delete_article"), data);
    }

    @Override
    public int update(Article data) {
        return super.update(NAMESPACE.concat("update_article"), data);
    }

    @Override
    public int updateStatus(Article data) {
        return super.update(NAMESPACE.concat("update_article_status"), data);
    }

    @Override
    public Article select(Article condition) {
        return super.select(NAMESPACE.concat("select_article"), condition,
            Article.class);
    }

    @Override
    public long selectTotalCount(Article condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_count_article"),
            condition);
    }

    @Override
    public List<Article> selectList(Article condition) {
        return super.selectList(NAMESPACE.concat("select_article"), condition,
            Article.class);
    }

    @Override
    public List<Article> selectList(Article condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_article_list"), start,
            count, condition, Article.class);
    }
}
