package com.springboot.mapper;

import com.springboot.entity.Gwc;
import com.springboot.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {


    @Insert("INSERT INTO t_order(order_id,order_address,order_message,order_price,order_bus,order_user,order_date,order_isOk)" +
            "VALUES(#{order.order_id},#{order.order_address},#{order.order_message},#{order.order_price},#{order.order_bus},#{order.order_user},#{order.order_date},1)")
   void addOrder(@Param("order")Order order);
}
