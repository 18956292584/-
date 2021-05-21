package com.springboot.mapper;

import com.springboot.entity.Evaluate;
import com.springboot.entity.Gwc;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EvaluateMapper {


    @Insert("INSERT INTO t_evaluate(e_order_id,e_user_id,e_food_id,e_evaluate,e_date,e_b_id)" +
            "VALUES(#{evaluate.e_order_id},#{evaluate.e_user_id},#{evaluate.e_food_id},#{evaluate.e_evaluate},#{evaluate.e_date},#{evaluate.e_b_id})")
    void add(@Param("evaluate") Evaluate evaluate);
}
