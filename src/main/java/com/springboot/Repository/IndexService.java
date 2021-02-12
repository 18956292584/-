package com.springboot.Repository;

import com.springboot.entity.Business;
import com.springboot.entity.Food;

import java.util.ArrayList;

public interface IndexService {
    //获取所有食物
    public ArrayList<Food> allFood();

    //获取所有商家
    public ArrayList<Business> allbusiness();
}
