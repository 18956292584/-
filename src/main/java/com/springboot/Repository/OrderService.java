package com.springboot.Repository;

import com.springboot.entity.Order;
import com.springboot.entity.OrderModel;

import java.util.List;

public interface OrderService {
    List<Order> dfk(int userId,int state);

    Object getorderModel(int order_id);

    OrderModel dealConfirmOrder(int userId, String gwcId);

    String submitOrder(Order order);
}
