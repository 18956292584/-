package com.springboot.mapper;

import com.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    //根据用户id获取用户
    User getUser(String id);

    //添加用户
    int insertUser(User user);

    @Update("update user set user_password = #{user.user_password},user_email = #{user.user_email},user_phone = #{user.user_phone},user_name = #{user.user_name} where user_id = #{user.user_id}")
    void changeUserInfo(@Param("user")User user);
}
