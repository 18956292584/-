package com.springboot.Repository;

import com.springboot.entity.Business;
import com.springboot.entity.Evaluate;
import com.springboot.entity.EvaluateModel;
import com.springboot.entity.Food;

import java.util.ArrayList;

public interface IndexService {
    //获取所有食物
    public ArrayList<Food> allFood();

    //获取所有商家
    public ArrayList<Business> allbusiness();

    //获取所有食物
    public ArrayList<Food> top3fod();

    //获取商家
    public Business getshop(int shopid);

    //获取商家食物
    public ArrayList<Food> shopfood(int shopid);

    //获取其他商家
    public ArrayList<Business> othershop(int shopid);

    //获取前10条评价
    public ArrayList<EvaluateModel> top10evaluate();

    public ArrayList<EvaluateModel> evaluateByshop(int shopid);

    public Food getFood(int foodid);

    public ArrayList<Food> top3food();

}
