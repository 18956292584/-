package com.springboot.Repository.Impl;

import com.springboot.Repository.IndexService;
import com.springboot.entity.Business;
import com.springboot.entity.Evaluate;
import com.springboot.entity.EvaluateModel;
import com.springboot.entity.Food;
import com.springboot.mapper.MainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    MainMapper main;


    @Override
    public ArrayList<Food> allFood() {
        return main.allfood();
    }

    @Override
    public ArrayList<Business> allbusiness() {
        return main.allbusiness();
    }

    @Override
    public ArrayList<Food> top3fod() {
        return main.top3food();
    }

    @Override
    public Business getshop(int shopid) {
        return main.getshop(shopid);
    }

    @Override
    public ArrayList<Food> shopfood(int shopid) {
        return main.shopFood(shopid);
    }

    @Override
    public ArrayList<Business> othershop(int shopid) {
        return main.othershop(shopid);
    }

    @Override
    public ArrayList<EvaluateModel> top10evaluate() {
        return main.top10evaluate();
    }

    @Override
    public ArrayList<EvaluateModel> evaluateByshop(int shopid) {
        return main.evaluateByshop(shopid);
    }

    @Override
    public Food getFood(int foodid) {
        return main.getFood(foodid);
    }

    @Override
    public ArrayList<Food> top3food() {
        return main.top3food();
    }
}
