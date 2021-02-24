package com.springboot.Repository.Impl;

import com.springboot.Repository.OrderService;
import com.springboot.entity.Business;
import com.springboot.entity.Gwc;
import com.springboot.entity.Order;
import com.springboot.entity.OrderModel;
import com.springboot.mapper.AddressMapper;
import com.springboot.mapper.BusinessMapper;
import com.springboot.mapper.GwcMapper;
import com.springboot.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    GwcMapper gwcMapper;
    @Autowired
    BusinessMapper businessMapper;
    @Autowired
    AddressMapper addressMapper;


    @Override
    public List<Order> dfk(int userId,int state) {
        return orderMapper.getOrder(userId,state);
    }

    @Override
    public OrderModel getorderModel(int order_id) {
        Order order =  orderMapper.getOrderinfo(order_id);
        ArrayList<Gwc> list =  gwcMapper.getAllItemsByBusId(order.getOrder_bus(),order.getOrder_user());
        Business business = businessMapper.getBus(order.getOrder_bus());


        OrderModel model = new OrderModel();
        model.setOrder_food(list);
        model.setAddress(addressMapper.getAddressByid(order.getOrder_address()));
        model.setBusiness(business);
        model.setOrder(order);

        return model;
    }

    @Override
    public OrderModel dealConfirmOrder(int userId, String gwcId) {
        String[] str = gwcId.split("AND");
        OrderModel model = new OrderModel();
        double num = 0;
        for (String st : str){
            Gwc g = gwcMapper.findByUserId(Integer.valueOf(st),userId);
            num += g.getFood_count()*g.getFood_price();
            model.getOrder_food().add(g);
        }
        int order_bus = gwcMapper.getBusIdByGwcId(Integer.valueOf(str[0]));
        model.setOrder_price(num);
        model.setOrder_user(userId);
        model.setOrder_bus(order_bus);

        return model;
    }

    @Override
    public String submitOrder(Order order) {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        order.setOrder_date(ft.format(date));
        Integer orderId = UUID.randomUUID().toString().hashCode();
        orderId = orderId < 0 ? -orderId : orderId;
        String uuid = String.valueOf(orderId);
        order.setOrder_id(uuid);
        orderMapper.addOrder(order);
        gwcMapper.updateState(order.getOrder_user(),order.getOrder_bus());

        return uuid;
    }
}
