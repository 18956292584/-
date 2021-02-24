package com.springboot.Repository;


import com.springboot.entity.*;

import java.util.List;

public interface UserService {
    //用户登录
    String dealLogin(User user);

    //用户注册
    String registerUser(User user);

    //更改购物车食物数量
    void updateNum(int custId, int foodId, int num);

    //删除购物车食物
    void removeGwc(int custid, int foodid);

    void addGwc(Gwc gwc);

    List<Address> getAddress(int userId);

}
