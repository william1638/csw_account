package com.std.account.cms.bo;

import java.util.List;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.cms.domain.Article;

public interface IArticleBO extends IPaginableBO<Article> {
    /**
     * 判断是否存在
     * @param code
     * @return 
     * @create: 2016年1月5日 下午7:39:14 xieyj
     * @history:
     */
    public boolean isArticleExist(String code);

    /**
     * 文章保存
     * @param data
     * @return 
     * @create: 2016年1月5日 下午7:39:32 xieyj
     * @history:
     */
    public int saveArticle(Article data);

    /**
     * 文章删除
     * @param data
     * @return 
     * @create: 2016年1月5日 下午7:39:32 xieyj
     * @history:
     */
    public int removeArticle(String code);

    /**
     * 文章修改
     * @param data
     * @return 
     * @create: 2016年1月5日 下午7:40:30 xieyj
     * @history:
     */
    public int refreshArticle(Article data);

    /**
     * 文章状态修改
     * @param code
     * @param status
     * @param updater
     * @return 
     * @create: 2016年1月5日 下午7:40:45 xieyj
     * @history:
     */
    public int refreshArticleStatus(String code, String status, String updater);

    /**
     * 获取某个文章
     * @param code
     * @return 
     * @create: 2016年1月5日 下午7:39:54 xieyj
     * @history:
     */
    public Article getArticle(String code);

    /**
     * 文章列表查询
     * @param data
     * @return 
     * @create: 2016年1月5日 下午7:40:14 xieyj
     * @history:
     */
    public List<Article> queryArticleList(Article data);
}
