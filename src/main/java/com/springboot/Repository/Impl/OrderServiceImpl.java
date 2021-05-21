package com.springboot.Repository.Impl;

import com.alipay.api.AlipayApiException;
import com.springboot.Repository.OrderService;
import com.springboot.entity.*;
import com.springboot.mapper.AddressMapper;
import com.springboot.mapper.BusinessMapper;
import com.springboot.mapper.GwcMapper;
import com.springboot.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public List<Order> dfk(String userId,int state) {
        return orderMapper.getOrder(userId,state);
    }

    @Override
    public OrderModel getorderModel(String order_id) {
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
    public OrderModel dealConfirmOrder(String userId, String gwcId) {
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

    @Override
    public String ckeckOrder(HttpServletRequest request, HttpServletResponse response) {
        String str = null;
        try {
            if(PayAction.check(request,response)){
                str =  check2(PayAction.convertRequestParamsToMap(request));
            } else {
                throw new Exception("验证失败");
            };
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }



    private  String  check2(Map<String, String> params) throws AlipayApiException {
        String outTradeNo = params.get("out_trade_no");
        // 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
        //查看数据库中是否有该订单存在
        Order order = orderMapper.getOrderinfo(outTradeNo); // 这个方法自己实现
        if (order == null) {
            throw new AlipayApiException("out_trade_no错误");
        }

        // 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
        double total_amount = Double.valueOf(params.get("total_amount"));
        //这里使用的BigDecimal 类型进行比较使用compareTo方法比较好，相等会返回0
        if (total_amount != order.getOrder_price()) {
            throw new AlipayApiException("error total_amount");
        }

        // 3、校验通知中的seller_id（卖家支付宝用户号）（或者seller_email--收款账号)是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
        // 第三步可根据实际情况省略

        // 4、验证app_id是否为该商户本身。
        if (!params.get("app_id").equals(AppPayConfig.app_id)) {
            throw new AlipayApiException("app_id不一致");
        }
        orderMapper.setOrderState(outTradeNo,1);

        return order.getOrder_user();
    }

    @Override
    public void deleteOrder(String orderId) {
        orderMapper.deleteOrder(orderId);
    }

    @Override
    public void confirmReceipt(String order_id) {
        orderMapper.setOrderState(order_id,3);
    }

    @Override
    public List<Order> dfkBus(String bus, int i) {
        return orderMapper.getOrderByBus(bus,i);
    }

    @Override
    public void order_fh(String order_id) {
        orderMapper.setOrderState(order_id,2);


        //todo
    }
}
