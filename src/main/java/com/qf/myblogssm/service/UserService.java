package com.qf.myblogssm.service;

import com.qf.myblogssm.pojo.User;
/**
 * 用户的服务层
 * */
public interface UserService {
    /**
     * 用户的登录接口
     * @param username
     * @param password
     * @return
     */
    User login(String username, String password);
}
