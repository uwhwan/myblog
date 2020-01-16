package com.qf.myblogssm.dao;

import com.qf.myblogssm.pojo.Article;
import com.qf.myblogssm.pojo.Comment;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    Article selectByPrimaryKey(Integer id);

    List<Article> selectAll();

    int updateByPrimaryKey(Article record);

    /**
     * 查询sort数
     * @return
     */
    Integer selectSort();

    /**
     *查询日志(articles)数
     * @return
     */
    Integer selectArticle();

    /**
     * 查询文章的分类及个数
     * @return
     */
    List<Article> selectSortAndCount();

    /**
     * 增加点赞次数
     * @param id
     */
    void updateStar(Integer id);

    List<Comment> selectAllComments(Integer id);

    /**
     * 按照浏览数排序
     * @return
     */
    List<Article> selectVisit();


    /**
     * 通过sort查询文章
     * @param sort
     * @return
     */
    List<Article> selectArticleBysort(String sort);

    /**
     * 通过tag来查询文章
     * @param tag
     * @return
     */
    List<Article> selectArticleByTag(String tag);

    /**
     * 通过id查文章，返回上一篇文章
     * @param id
     * @return
     */
    Article selectPreArticle(Integer id);

    /**
     * 通过id查文章，返回下一篇文章
     * @param id
     * @return
     */
    Article selectNextArticle(Integer id);
}