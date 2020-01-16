package com.qf.myblogssm.controller;

import com.qf.myblogssm.pojo.Article;
import com.qf.myblogssm.service.ArticleService;
import com.qf.myblogssm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/getTagInfo")
    public String toTag(Model model){

        Map<String, List<Article>> tag_map = articleService.getTagAndArticle();
        model.addAttribute("tag_map",tag_map);

        System.out.print(tag_map);
        return "tags";
    }

}
