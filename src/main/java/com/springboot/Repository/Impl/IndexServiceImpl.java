package com.springboot.Repository.Impl;

import com.springboot.Repository.IndexService;
import com.springboot.entity.Business;
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
}
