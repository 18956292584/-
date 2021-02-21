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

import java.util.ArrayList;
import java.util.List;
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
}
