package com.qf.myblogssm.serviceImpl;

import com.qf.myblogssm.dao.CommentMapper;
import com.qf.myblogssm.pojo.Comment;
import com.qf.myblogssm.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    //查找评论并显示出来
    @Override
    public List<Comment> selectComments(Integer aId) {

        List<Comment> commentList = commentMapper.selectComments(aId);

        return commentList;
    }

    //删除评论
    @Override
    public int deleteByPrimaryKey(Integer id) {
         return commentMapper.deleteByPrimaryKey(id);
    }



    //插入评论功能
    @Override
    public int insert(Comment comment) {
        return commentMapper.insert(comment);
    }

    //查询评论并显示出来
    @Override
    public List<Comment> selectAll() {
        return commentMapper.selectAll();
    }
}
