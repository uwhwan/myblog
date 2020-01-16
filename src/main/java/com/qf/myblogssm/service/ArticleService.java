package com.qf.myblogssm.service;

import com.qf.myblogssm.pojo.Article;

import java.util.List;
import java.util.Map;

/**
 * 文章的服务层接口
 */
public interface ArticleService {
    /**
     * 获取所有文章
     * @return
     */
    List<Article> selectAll();

    Integer selectSort();

    Integer selectArticle();


    /**
     * 获取文章的分类以及分类的个数
     * @return
     */
    Map<String, Integer> getSortAndCount();

    /**
     * 通过id获取到文章
     * @return
     */
    Article selectById(Integer id);

    /**
     * 增加点赞次数
     * @param id
     */
    void star(Integer id);

    /**
     * 按照浏览数排序
     * @return
     */
    List<Article> selectVisit();

    /**
     * 获取各分类的文章
     * @return
     */
    Map<String, List<Article>> getSortAndArticle();

    /**
     * 根据tag分类查询
     * @return
     */
    Map<String, List<Article>> getTagAndArticle();

    /**
     * 返回上一篇文章
     * @param id
     * @return
     */
    Article selectPre(Integer id);

    /**
     * 返回下一篇文章
     * @param id
     * @return
     */
    Article selectNext(Integer id);
}
