package com.qf.myblogssm.serviceImpl;

import com.qf.myblogssm.dao.ArticleMapper;
import com.qf.myblogssm.dao.TagMapper;
import com.qf.myblogssm.pojo.Article;
import com.qf.myblogssm.pojo.Tag;
import com.qf.myblogssm.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Article> selectAll() {
        //还有业务逻辑没有写
        List<Article> articles = articleMapper.selectAll();
        //处理文章内容
        //iter，增强for循环
        for (Article article : articles) {
            //判断文章的内容是否大于30个字符
            if (article.getContent().length()>30){
//                String content = article.getContent();
//                //对字符串进行剪切   substring方法的参数取前不取后
//                String substring = content.substring(0, 30)+"......";
                article.setContent(article.getContent().substring(0,30)+"......");
            }
        }
        return articles;
    }

    @Override
    public Integer selectSort() {

        Integer sort = articleMapper.selectSort();
        return sort;
    }

    @Override
    public Integer selectArticle() {

        Integer article = articleMapper.selectArticle();
        return article;
    }

    //获取分类及其对应的数量
    @Override
    public Map<String, Integer> getSortAndCount() {
        //在article类中添加一个分类数量的属性   通过实体类将数据返回到service中
        //然后遍历list集合，将需要的数据添加到对应的map集合中
        List<Article> articleList = articleMapper.selectSortAndCount();

        Map<String,Integer> maps = new HashMap<String, Integer>();

        for (Article article : articleList) {
            maps.put(article.getSort(),article.getCountsort());
        }

        return maps;
    }

    //通过id查找文章即阅读全文
    @Override
    public Article selectById(Integer id) {

        Article article = articleMapper.selectByPrimaryKey(id);

        return article;
    }

    //执行增加 删除 修改 操作时要在服务层添加事务
    @Transactional
    @Override
    public void star(Integer id) {
        //如果在服务层捕获异常，并且进行事务的回滚  必须抛出运行时异常
        articleMapper.updateStar(id);
    }

    //获取评论数并按评论数排序
    @Override
    public List<Article> selectVisit() {

        return articleMapper.selectVisit();
    }

    //获取各分类的文章
    @Override
    public Map<String, List<Article>> getSortAndArticle() {

        List<Article> articleList = articleMapper.selectSortAndCount();
        Map<String,List<Article>> artMap = new HashMap<String, List<Article>>();

        for (Article article : articleList) {
            artMap.put(article.getSort(),articleMapper.selectArticleBysort(article.getSort()));
        }
        return artMap;

    }

    //根据tag分类查询
    @Override
    public Map<String, List<Article>> getTagAndArticle() {

        List<Tag> tagList = tagMapper.selectAll();
        Map<String,List<Article>> tagArt = new HashMap<String, List<Article>>();

        for (Tag tag : tagList) {
            tagArt.put(tag.getTag(),articleMapper.selectArticleByTag(tag.getTag()));
        }

        return tagArt;

    }

    //返回上一篇文章
    @Override
    public Article selectPre(Integer id) {

        return articleMapper.selectPreArticle(id);
    }

    @Override
    public Article selectNext(Integer id) {
        return articleMapper.selectNextArticle(id);
    }

}
