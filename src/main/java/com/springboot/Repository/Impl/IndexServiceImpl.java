package com.springboot.Repository.Impl;

import com.springboot.Repository.IndexService;
import com.springboot.entity.*;
import com.springboot.mapper.GwcMapper;
import com.springboot.mapper.MainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    MainMapper main;

    @Autowired
    GwcMapper gwc;


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

    @Override
    public HashMap<String,ArrayList<Gwc>> getGwc(int userId) {
        ArrayList<Gwc> list = gwc.getAllItems(userId);

        HashMap<String,ArrayList<Gwc>> map = new HashMap<String,ArrayList<Gwc>>();

        for(Gwc gwc : list){
            String str = gwc.getBus_name();
            System.out.println(str);
            if(map.containsKey(str)){
                ArrayList<Gwc> lists =  map.get(str);
                lists.add(gwc);
                map.put(str,lists);
            } else {
                ArrayList<Gwc> lists =  new ArrayList<>();
                lists.add(gwc);
                map.put(str,lists);
            }
        }

        System.out.println(map.size());
        return map;
    }

    @Override
    public ArrayList<Business> otherShop() {
        return main.otherShop();
    }
}
