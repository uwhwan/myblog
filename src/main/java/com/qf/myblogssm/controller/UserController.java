package com.qf.myblogssm.controller;

import com.qf.myblogssm.pojo.Article;
import com.qf.myblogssm.pojo.Tag;
import com.qf.myblogssm.pojo.User;
import com.qf.myblogssm.service.ArticleService;
import com.qf.myblogssm.service.TagService;
import com.qf.myblogssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 *用户控制层
 */
@Controller
@RequestMapping("/users")
public class UserController {
    //设置路径的访问是通过接口文档来进行设置
    //根据名称进行注入
    //@Resource
    //自动装配的注解
    //根据类型进行注入
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request,Model model){
        //会去Redis数据库中判断用户的token令牌
        //先判断session里面是否有一个用户 如果有则直接进入主界面，如果没有则进入登录界面
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user!=null){
            //应该将session的过期时间进行刷新
            session.setMaxInactiveInterval(300);
            //需要加载静态页面信息
            ini(model);

            //http://localhost:8080:/myblogssm_war_exploded/jsp/main.jsp
            //jsp.main.jsp
            //request.getParameter("");
            return "main";
        }else{
            return "login";
        }
    }
    @RequestMapping("/login")
    public String login(String username, String password, HttpServletRequest request, Model model){
        //调用服务层   声明一个服务层对象
        User user = userService.login(username, password);
       //返回我们需要的界面  login.jsp     main.jsp
        if (user!=null){
            //返回main.jsp
            //将登录成功的user放到session中并且设置过期时间
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            //设置过期时间  单位是秒
            session.setMaxInactiveInterval(300);
            //携带着主页面的初始化信息
            //实现文章列表的携带   操作文章的服务层
            //需要文章的服务层提供一个函数    并且该函数需要返回一个存放着Article
            ini(model);

            return "main";
        }else {
            //携带错误信息返回
            //model.addAttribute("msg","用户名或密码错误");
            request.setAttribute("msg","用户名或密码错误");
            return "login";
        }
    }

    @RequestMapping("/loginout")
    public String loginout(HttpServletRequest request){
        request.getSession().setAttribute("user","null");
        return "login";
    }

    //封装一个初始化页面信息的方法
    public void ini(Model model){
        //需要文章的服务层提供一个函数    并且该函数需要返回一个存放着Article
        List<Article> articleList= articleService.selectAll();
        model.addAttribute("article_list",articleList);

        //实现文章个数    分类个数    标签个数
        //实现分类统计      sort_count_map   value值为分类统计个数
        Map<String,Integer>  sort_count_map = articleService.getSortAndCount();
        model.addAttribute("sort_count_map",sort_count_map);

        //作业
        //标签个数
        Integer selectTag = tagService.selectTag();
        model.addAttribute("tag_number",selectTag);

        //分类个数
        Integer selectSort = articleService.selectSort();
        model.addAttribute("sort_number",selectSort);

        //日志个数
        Integer selectArticle = articleService.selectArticle();
        model.addAttribute("article_number",selectArticle);

        //查询浏览次数
        List<Article> article_list = articleService.selectVisit();
        model.addAttribute("article_list",article_list);

        //查询标签数，并显示在初始化页面上
        List<Tag> tag_list = tagService.selectAll();
        model.addAttribute("tag_list",tag_list);
    }
}
