package com.springboot.Repository;


import com.springboot.entity.*;

import java.util.List;

public interface UserService {
    //获取用户id和密码
    public User dealLogin(String id, String password);

    //更改购物车食物数量
    void updateNum(int custId, int foodId, int num);

    //删除购物车食物
    void removeGwc(int custid, int foodid);

    void addGwc(Gwc gwc);

    OrderModel dealConfirmOrder(int userId, String gwcId);

    List<Address> getAddress(int userId);

    String submitOrder(Order order);
}
