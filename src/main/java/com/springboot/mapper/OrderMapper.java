package com.springboot.mapper;

import com.springboot.entity.Gwc;
import com.springboot.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {


    @Insert("INSERT INTO t_order(order_id,order_address,order_message,order_price,order_bus,order_user,order_date,order_isOk,order_state)" +
            "VALUES(#{order.order_id},#{order.order_address},#{order.order_message},#{order.order_price},#{order.order_bus},#{order.order_user},#{order.order_date},1,0)")
   void addOrder(@Param("order")Order order);

    @Select("select * from t_order where order_state = #{state} and order_user = #{userId}")
    List<Order> getOrder(@Param("userId")int userId, @Param("state")int state);

    @Select("select * from t_order where order_id = #{order_id}")
    Order getOrderinfo(int order_id);
}
