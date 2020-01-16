package com.qf.myblogssm.serviceImpl;

import com.qf.myblogssm.dao.UserMapper;
import com.qf.myblogssm.pojo.User;
import com.qf.myblogssm.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username,String password){
        //通过用户名去数据库查找对应的用户
        User user = userMapper.selectByName(username);
        //如果用户不为null，则进行密码对比
        //如果密码为null，则不需要进行密码操作  直接返回null值
        if (user!=null){
            //首先要将前端传过来的密码进行加密以后在对比
            Md5Hash md5Hash = new Md5Hash(password, username, 1000);

            if(user.getUserPassword().equals(md5Hash.toString())) {
                return user;
            }
        }
        return null;
    }
}
