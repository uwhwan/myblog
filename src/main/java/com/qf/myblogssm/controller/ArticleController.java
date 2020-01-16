package com.qf.myblogssm.controller;

import com.qf.myblogssm.pojo.Article;
import com.qf.myblogssm.service.ArticleService;
import com.qf.myblogssm.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @RequestMapping("/getSortInfo")
    public String toSort(Model model){

        Map<String, List<Article>> sort_article_map = articleService.getSortAndArticle();
        model.addAttribute("sort_article_map",sort_article_map);

        return "sort";
    }


    //需要一个方法获取到当前的点击的文章  放在共享域中   返回article.jsp
    @RequestMapping("/getById")
    public String selectById(Integer id, Model model){

        Article article = articleService.selectById(id);
        model.addAttribute("article",article);

        //返回上一篇文章
        Article article_pre = articleService.selectPre(id);
        model.addAttribute("article_pre",article_pre);

        //返回下一篇文章
        Article article_next = articleService.selectNext(id);
        model.addAttribute("article_next",article_next);

        //查询评论
        model.addAttribute("comment",commentService.selectAll());
        return "article";
    }

    //通过异步的方式实现点赞
    //通过点赞按钮，发送异步请求
    //接收请求后
    //通过文章的id确定点赞的文章  然后数据库中将文章的点赞次数+1
    //返回一个点赞次数给前端

    //http://localhost:8080/myblogssm/article/star?id=10
    //如果是ajax请求  返回值放在ajax请求对象中
    @RequestMapping("/star")
    @ResponseBody   //将数据转换为Json格式
    public Article star(Integer id){
//        //需要处理
        try {
            articleService.star(id);
            Article article = articleService.selectById(id);
            return article;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

//    @RequestMapping("/star")
//    @ResponseBody   //将数据转换为Jason格式
//    public Message star(Integer id){
//      try {
//          articleService.star(id);
//          Article article = articleService.selectById(id);
//          Message msg = new Message();
//          msg.setObject(article);
//          msg.setEcode("200");
//          return msg;
//      }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }

}
