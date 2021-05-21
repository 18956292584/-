package com.springboot.mapper;

import com.springboot.entity.Food;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface FoodMapper {

    @Select("select * from t_food where f_b_id = #{busId} and f_state != 0")
    ArrayList<Food> getBusFood(@Param("busId") String busId);

    @Select("select * from t_food where f_b_id = #{busId} and f_state = ${state}")
    ArrayList<Food> getBusFoodByState(@Param("busId") String busId, @Param("state")int state);

    @Insert("INSERT INTO t_food(f_name,f_stock,f_sales_volume,f_b_id,f_state,f_introduce,f_pic,f_price)" +
            "VALUES(#{f.f_name},#{f.f_stock},#{f.f_sales_volume}," +
            "#{f.f_b_id},#{f.f_state},#{f.f_introduce}," +
            "#{f.f_pic},#{f.f_price})")
    void addFood(@Param("f") Food food);

    @Update("UPDATE t_food SET f_state = #{state} WHERE f_id = #{foodid}")
    void setFoodState(@Param("foodid")int foodid,@Param("state") int state);


    @Update("UPDATE t_food SET f_introduce = #{f.f_introduce},f_price = #{f.f_price},f_pic = #{f.f_pic} WHERE f_id = #{f.f_id}")
    void changeFoood(@Param("f") Food food);

    @Select("select * from t_food where f_b_id = #{busId} and f_name like CONCAT('%',#{foodname},'%')")
    ArrayList<Food> searchFood(@Param("foodname")String food,@Param("busId") String bus);
}
