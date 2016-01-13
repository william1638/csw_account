package com.std.account.cms.ao;

import java.util.List;

import com.std.account.bo.base.Paginable;
import com.std.account.cms.domain.Article;

public interface IArticleAO {
    String DEFAULT_ORDER_COLUMN = "order_no";

    /**
     * 文章增加
     * @param Article
     * @return 
     * @create: 2015年8月25日 下午3:18:51 myb858
     * @history:
     */
    public boolean addArticle(Article data);

    /**
     * 文章删除
     * @param code
     * @return 
     * @create: 2015年8月25日 下午3:19:28 myb858
     * @history:
     */
    public boolean dropArticle(String code);

    /**
     * 文章修改
     * @param data
     * @return 
     * @create: 2015年8月25日 下午3:21:17 myb858
     * @history:
     */
    public boolean editArticle(Article data);

    /**
     * 更改文章状态
     * @param code
     * @param status
     * @param updater
     * @return 
     * @create: 2016年1月5日 下午6:04:09 xieyj
     * @history:
     */
    public boolean changeArticleStatus(String code, String status,
            String updater);

    /**
     * 单个文章查询
     * @param code
     * @return 
     * @create: 2015年8月25日 下午3:20:59 myb858
     * @history:
     */
    public Article getArticle(String code);

    /**
     * 列表查询
     * @param condition
     * @return 
     * @create: 2015年8月25日 下午3:20:59 myb858
     * @history:
     */
    public List<Article> queryArticleList(Article condition);

    /** 
     * 分页查询
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2015年8月25日 下午3:20:28 myb858
     * @history:
     */
    public Paginable<Article> queryArticlePage(int start, int limit,
            Article condition);

}
