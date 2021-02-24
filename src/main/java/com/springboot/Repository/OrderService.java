package com.springboot.Repository;
import com.springboot.entity.Order;
import com.springboot.entity.OrderModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface OrderService {
    List<Order> dfk(int userId,int state);

    Object getorderModel(String order_id);

    OrderModel dealConfirmOrder(String userId, String gwcId);

    String submitOrder(Order order);

    String ckeckOrder(HttpServletRequest request, HttpServletResponse response);
}

