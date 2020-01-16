package com.qf.myblogssm.service;

import com.qf.myblogssm.pojo.Article;
import com.qf.myblogssm.pojo.Tag;

import java.util.List;
import java.util.Map;

public interface TagService {
    /**
     * 查询tag数
     * @return
     */
    Integer selectTag();

    /**
     * 查询标签数，并显示在初始化页面上
     * @return
     */
    List<Tag> selectAll();


}
