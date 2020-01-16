package com.qf.myblogssm.service;

import com.qf.myblogssm.pojo.Comment;

import java.util.List;

public interface CommentService {

    /**
     * 查找评论，并将评论显示在页面上
     * @param aId
     * @return
     */
    List<Comment> selectComments(Integer aId);

    /**
     * 删除评论
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入评论
     * @param comment
     * @return
     */
    int insert(Comment comment);

    /**
     * 查询评论并显示出来
     * @return
     */
    List<Comment> selectAll();
}
