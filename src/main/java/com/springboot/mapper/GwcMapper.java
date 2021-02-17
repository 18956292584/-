package com.springboot.mapper;

import com.springboot.entity.Gwc;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.ArrayList;

@Mapper
public interface GwcMapper {
//    public int add(Gwc gwc);

    @Select("update t_gwc set food_count = #{food_count} where cust_id = #{cust_id} and food_id = #{food_id} and gwc_state != 0")
    public void updateNum(@Param("food_count")int food_count, @Param("cust_id")int cust_id, @Param("food_id")int food_id);

//    public Gwc findById(int custId, int bookId);
//
//    public float getPrice(int custId);

    @Select("select * from t_gwc where cust_id = #{custId} and gwc_state != 0")
    public ArrayList<Gwc> getAllItems(int custId);


    @Select("update t_gwc set gwc_state = 0 where cust_id = #{cust_id} and food_id = #{food_id}")
    void removeGwc( @Param("cust_id")int cust_id, @Param("food_id")int food_id);
}
