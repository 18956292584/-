package com.springboot.Repository;


import com.springboot.entity.*;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    //用户登录
    String dealLogin(User user);

    //用户注册
    String registerUser(User user);

    //更改购物车食物数量
    void updateNum(String custId, int foodId, int num);

    //删除购物车食物
    void removeGwc(int custid, int foodid);

    void addGwc(Gwc gwc);

    List<Address> getAddress(String userId);

    void addAddress(Address address);

    void ChangeAddress(Address address);

    void DeletAddress(Address address);

    User getUser(String userId);

    void changeUserInfo(User user);

    void dealPJ(ArrayList<Integer> e_food_id, ArrayList<String> e_evaluate, ArrayList<String> e_user_id, ArrayList<String> e_order_id, ArrayList<String> e_b_id, double order_pf);
}
