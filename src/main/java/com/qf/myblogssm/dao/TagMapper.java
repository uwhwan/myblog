package com.qf.myblogssm.dao;

import com.qf.myblogssm.pojo.Article;
import com.qf.myblogssm.pojo.Tag;

import java.util.List;

public interface TagMapper {
    int insert(Tag record);

    List<Tag> selectAll();

    Integer selectTag();

    /**
     * 获取所有tag
     * @return
     */
    List<Tag> selectTagList();



}