package com.springboot.mapper;

import com.springboot.entity.Business;
import com.springboot.entity.Food;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface MainMapper {

    @Select("select * from t_food")
    ArrayList<Food> allfood();

    @Select("select * from t_business")
    ArrayList<Business> allbusiness();
}
