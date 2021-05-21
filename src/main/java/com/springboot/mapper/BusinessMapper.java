package com.springboot.mapper;

import com.springboot.entity.Business;
import com.springboot.entity.Food;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

@Mapper
public interface BusinessMapper {


    @Select("select * from t_business where b_id = #{order_bus}")
    Business getBus(String order_bus);


    @Update("UPDATE t_business SET t_business.b_score = (t_business.b_score + #{order_pf})/2 WHERE t_business.b_id = 1")
    void updateScore(double order_pf);



}
