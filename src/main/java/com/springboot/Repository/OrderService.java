package com.springboot.Repository;
import com.springboot.entity.Order;
import com.springboot.entity.OrderModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface OrderService {
    List<Order> dfk(String userId,int state);

    Object getorderModel(String order_id);

    OrderModel dealConfirmOrder(String userId, String gwcId);

    String submitOrder(Order order);

    String ckeckOrder(HttpServletRequest request, HttpServletResponse response);

    void deleteOrder(String order_id);

    void confirmReceipt(String order_id);

    List<Order> dfkBus(String bus, int i);

    void order_fh(String order_id);
}

