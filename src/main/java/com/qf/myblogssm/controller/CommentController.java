package com.qf.myblogssm.controller;

import com.qf.myblogssm.pojo.Comment;
import com.qf.myblogssm.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    //删除评论
    @RequestMapping("/del")
    @Transactional
    @ResponseBody
    public int deleteByPrimaryKey(Model model ,Integer id){
        return commentService.deleteByPrimaryKey(id);
    }

    //添加评论
    @RequestMapping("/addComments")
    @Transactional
    @ResponseBody   //将数据转换为json格式
    public int addComment(Model model, Integer article_id, String nickname, Date time, String content){
        Comment comment = new Comment();
         comment.setArticleId(article_id);
         comment.setNickname(nickname);
         comment.setTime(time);
         comment.setContent(content);

        int res = commentService.insert(comment);

        model.addAttribute("comment",comment);

        return res;
    }
}
