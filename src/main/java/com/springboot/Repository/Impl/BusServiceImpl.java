package com.springboot.Repository.Impl;

import com.springboot.Repository.BusService;
import com.springboot.entity.Business;
import com.springboot.entity.Food;
import com.springboot.entity.IdPasswordModel;
import com.springboot.entity.User;
import com.springboot.mapper.BusinessMapper;
import com.springboot.mapper.FoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BusServiceImpl implements BusService {

    @Autowired
    BusinessMapper businessMapper;

    @Autowired
    FoodMapper foodMapper;

    @Override
    public String dealLogin(IdPasswordModel idPasswordModel) {
        //通过用户账号获取用户
        Business user_login = businessMapper.getBus(idPasswordModel.getId());
        //获取失败
        if (user_login==null) {
            return "该账号不存在";
        }
        //密码错误
        if (!user_login.getB_password().equals(idPasswordModel.getPassword())){
            return "密码错误";
        }
        //若账号存在密码相同，登录成功
        return "登录成功";
    }

    @Override
    public ArrayList<Food> getAllFood(String bus, int state) {
        return foodMapper.getBusFoodByState(bus,state);
    }

    @Override
    public void addFood(Food food) {
        food.setF_sales_volume(0);
        food.setF_state(1);
        food.setF_stock(10000);


        foodMapper.addFood(food);
    }

    @Override
    public void foodState(int foodid, int state) {
        foodMapper.setFoodState(foodid,state);
    }

    @Override
    public void changeFood(Food food) {
        foodMapper.changeFoood(food);
    }

    @Override
    public ArrayList<Food> searchFood(String food, String bus) {
        return foodMapper.searchFood(food,bus);
    }

    @Override
    public Business getBusById(String bus) {
        return businessMapper.getBus(bus);
    }
}
