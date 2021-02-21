package com.springboot.mapper;

import com.springboot.entity.Gwc;
import org.apache.ibatis.annotations.*;


import java.util.ArrayList;

@Mapper
public interface GwcMapper {

    @Insert("INSERT INTO t_gwc(cust_id,food_id,bus_id,food_pic,food_price,food_count,food_name,bus_name,gwc_state)" +
            "VALUES(#{gwc.cust_id},#{gwc.food_id},#{gwc.bus_id},#{gwc.food_pic},#{gwc.food_price},#{gwc.food_count},#{gwc.food_name},#{gwc.bus_name},#{gwc.gwc_state})")
    void add(@Param("gwc")Gwc gwc);

    @Update("update t_gwc set food_count = #{food_count} where cust_id = #{cust_id} and food_id = #{food_id} and gwc_state != 0")
    void updateNum(@Param("food_count")int food_count, @Param("cust_id")int cust_id, @Param("food_id")int food_id);

//    public Gwc findById(int custId, int bookId);
//
//    public float getPrice(int custId);

    @Select("select * from t_gwc where cust_id = #{custId} and gwc_state != 0")
    ArrayList<Gwc> getAllItems(int custId);


    @Update("DELETE from t_gwc where cust_id = = #{cust_id} and food_id = #{food_id}")
    void removeGwc( @Param("cust_id")int cust_id, @Param("food_id")int food_id);

    @Select("SELECT food_count FROM t_gwc WHERE food_id = #{food_id} and cust_id = #{cust_id} and gwc_state != 0")
    ArrayList<Integer> findById( @Param("food_id")int food_id, @Param("cust_id")int cust_id);

    @Select("SELECT * FROM t_gwc WHERE gwc_id = #{gwc_id} and cust_id = #{cust_id} and gwc_state != 0")
    Gwc findByUserId(@Param("gwc_id")int gwc_id, @Param("cust_id")int cust_id);

    @Select("SELECT bus_id FROM t_gwc WHERE gwc_id = #{gwc_id}")
    Integer getBusIdByGwcId(int gwc_id);

    @Update("update t_gwc set gwc_state = 0 where cust_id = #{order_user} and bus_id = #{order_bus}")
    void updateState(@Param("order_user")int order_user, @Param("order_bus")int order_bus);

    @Select("select * from t_gwc where cust_id = #{custId} and bus_id = #{bus_id}")
    ArrayList<Gwc> getAllItemsByBusId(@Param("bus_id")int order_bus,@Param("custId")int cust_id);
}
