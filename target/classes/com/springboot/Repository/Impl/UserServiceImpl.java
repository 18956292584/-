package com.springboot.Repository.Impl;

import com.springboot.Repository.UserService;
import com.springboot.entity.User;
import com.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Parameter;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper user;

    /**
     * 根据用户账号查询用户信息
     * 得到用户信息 比对 输入的密码是否与用户信息密码一致
     * @param id
     * @param password
     * @return
     */
    @Override
    public User dealLogin(String id,String password) {
        User user = this.user.getUser(id);
        if (null != user && user.getUser_password().equals(password)) {
            return user;
        }
        return null;
    }
}
