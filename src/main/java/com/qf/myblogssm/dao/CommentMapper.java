package com.qf.myblogssm.dao;

import com.qf.myblogssm.pojo.Comment;
import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    Comment selectByPrimaryKey(Integer id);

    List<Comment> selectAll();

    int updateByPrimaryKey(Comment record);

    List<Comment> selectComments(Integer aId);
}