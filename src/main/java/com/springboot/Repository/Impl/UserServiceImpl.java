package com.springboot.Repository.Impl;

import com.springboot.Repository.UserService;
import com.springboot.entity.*;
import com.springboot.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    //购物车接口
    @Autowired
    GwcMapper gwc;

    //收货地址接口
    @Autowired
    AddressMapper address;

    //订单接口
    @Autowired
    OrderMapper orderMapper;

    //评价接口
    @Autowired
    EvaluateMapper evaluateMapper;

    //商家接口
    @Autowired
    BusinessMapper businessMapper;
    /**
     * 用户注册逻辑
     * @param user
     * @return
     */
    @Override
    public String registerUser(User user) {
        //判断账号是否存在
        if (userMapper.getUser(user.getUser_id())==null){
            return userMapper.insertUser(user) > 0 ? "注册成功" : "注册失败";
        }
        return "该账号已被使用";
    }


    /**
     * 用户登录逻辑
     * @param user
     * @return
     */
    @Override
    public String dealLogin(User user) {

        //通过用户账号获取用户
        User user_login = userMapper.getUser(user.getUser_id());
        //获取失败
        if (user_login==null) {
            return "该账号不存在";
        }
        //密码错误
        if (!user_login.getUser_password().equals(user.getUser_password())){
            return "密码错误";
        }
        //若账号存在密码相同，登录成功
        return "登录成功";
    }

    //更改购物车食物数量
    @Override
    public void updateNum(String custId, int foodId, int num) {
         gwc.updateNum(num,custId,foodId);
    }

    //删除购物车食物
    @Override
    public void removeGwc(int custid, int foodid) {
        System.out.println(custid+"," + foodid);
        gwc.removeGwc(custid,foodid);
    }

    @Override
    public void addGwc(Gwc g) {
        //SELECT food_count FROM t_gwc WHERE food_id = 8
        ArrayList<Integer> list = gwc.findById(g.getFood_id(),g.getCust_id());
        if(list.size() >= 1){
            gwc.updateNum(list.get(0) + g.getFood_count(),g.getCust_id(),g.getFood_id());
        } else {
            gwc.add(g);
        }


    }

    @Override
    public List<Address> getAddress(String userId) {
        return address.getAddress(userId);
    }

    @Override
    public void addAddress(Address address) {
        this.address.addAddress(address);
    }

    @Override
    public void ChangeAddress(Address address) {
        this.address.ChangeAddress(address);
    }

    @Override
    public void DeletAddress(Address address) {
        this.address.DeletAddress(address);
    }

    @Override
    public User getUser(String userId) {
        return this.userMapper.getUser(userId);
    }

    @Override
    public void changeUserInfo(User user) {
        this.userMapper.changeUserInfo(user);
    }

    @Override
    public void dealPJ(ArrayList<Integer> e_food_id, ArrayList<String> e_evaluate, ArrayList<String> e_user_id, ArrayList<String> e_order_id, ArrayList<String> e_b_id, double order_pf) {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = ft.format(date);
        for(int i = 0; i < e_food_id.size(); i++){
            if(e_evaluate.get(i) == ""){
                continue;
            }
            Evaluate evaluate = new Evaluate();
            evaluate.setE_date(str);
            evaluate.setE_evaluate(e_evaluate.get(i));
            evaluate.setE_food_id(e_food_id.get(i));
            evaluate.setE_order_id(e_order_id.get(0));
            evaluate.setE_user_id(e_user_id.get(i));
            evaluate.setE_b_id(e_b_id.get(i));
            evaluateMapper.add(evaluate);
        }

        businessMapper.updateScore(order_pf);
        orderMapper.setOrderState(e_order_id.get(0),4);

    }
}
