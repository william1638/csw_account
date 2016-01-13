package com.std.account.cms.dao;

import com.std.account.cms.domain.Article;
import com.std.account.dao.base.IBaseDAO;

public interface IArticleDAO extends IBaseDAO<Article> {
    String NAMESPACE = IArticleDAO.class.getName().concat(".");

    public int update(Article data);

    public int updateStatus(Article data);
}
