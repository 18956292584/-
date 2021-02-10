package com.springboot.Repository.Impl;

import com.springboot.Repository.UserService;
import com.springboot.entity.User;
import com.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper user;




    @Override
    public String dealLogin(String id,String password) {





        return "";
    }
}
