package com.qf.myblogssm.serviceImpl;

import com.qf.myblogssm.dao.ArticleMapper;
import com.qf.myblogssm.dao.TagMapper;
import com.qf.myblogssm.pojo.Article;
import com.qf.myblogssm.pojo.Tag;
import com.qf.myblogssm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ArticleMapper articleMapper;

    //获取tag数
    @Override
    public Integer selectTag() {

        Integer selectTag = tagMapper.selectTag();

        return selectTag;
    }

    //将所有tag去重显示在页面上
    @Override
    public List<Tag> selectAll() {
        return tagMapper.selectAll();
    }


}
