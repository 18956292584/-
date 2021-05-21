package com.springboot.Repository;

import com.springboot.entity.*;

import java.util.ArrayList;
import java.util.HashMap;

public interface IndexService {
    //获取所有食物
    public ArrayList<Food> allFood();

    //获取所有商家
    ArrayList<Business> allbusiness();

    //获取所有食物
    ArrayList<Food> top3fod();

    //获取商家
    Business getshop(int shopid);

    //获取商家食物
    ArrayList<Food> shopfood(int shopid);

    //获取其他商家
    ArrayList<Business> othershop(int shopid);

    //获取前10条评价
    ArrayList<EvaluateModel> top10evaluate();

    ArrayList<EvaluateModel> evaluateByfood(int shopid);

    Food getFood(int foodid);

    ArrayList<Food> top3food();

    HashMap<String,ArrayList<Gwc>> getGwc(String userId);

    ArrayList<Business> otherShop();

    Page setAllfoodPage(Page page);

    ArrayList<Food> allFood(Page page);

    ArrayList<Food> searchFood(String search_s);

    ArrayList<Business> allbusiness(Page page);

    Page setAllBusPage(Page page);

    ArrayList<Business> searchBus(String keyword);

    ArrayList<EvaluateModel> evaluateByshop(int shopid);
}
