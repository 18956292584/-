package com.springboot.mapper;

import com.springboot.entity.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {


    @Insert("INSERT INTO t_order(order_id,order_address,order_message,order_price,order_bus,order_user,order_date,order_isOk,order_state)" +
            "VALUES(#{order.order_id},#{order.order_address},#{order.order_message},#{order.order_price},#{order.order_bus},#{order.order_user},#{order.order_date},1,0)")
   void addOrder(@Param("order")Order order);

    @Select("select * from t_order where order_state = #{state} and order_user = #{userId} and order_isOk != 0")
    List<Order> getOrder(@Param("userId") String userId, @Param("state") int state);

    @Select("select * from t_order where order_id = #{order_id}")
    Order getOrderinfo(String order_id);

    @Update("update t_order set order_state = ${i} where order_id = #{outTradeNo}")
    void setOrderState(@Param("outTradeNo") String outTradeNo, @Param("i") int i);

    @Update("update t_order set order_isOk = 0 where order_id = #{orderId}")
    void deleteOrder(String orderId);

    @Select("select * from t_order where order_state = #{state} and order_bus = #{bus} and order_isOk != 0 order by order_date")
    List<Order> getOrderByBus(@Param("bus")String bus, @Param("state") int state);
}
