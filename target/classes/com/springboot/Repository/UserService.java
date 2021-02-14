package com.springboot.Repository;


import com.springboot.entity.User;

public interface UserService {
    //获取用户id和密码
    public User dealLogin(String id, String password);
}
