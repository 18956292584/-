package com.springboot.mapper;

import com.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    //根据用户id获取用户
    User getUser(String id);

    //添加用户
    int insertUser(User user);
}
