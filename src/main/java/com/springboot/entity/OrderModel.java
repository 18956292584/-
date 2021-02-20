package com.springboot.entity;

import java.util.ArrayList;
import java.util.List;

public class OrderModel {
    int order_bus;
    int order_user;
    double order_price;
    List<Gwc> order_food;


    public OrderModel(){

        order_food = new ArrayList<>();
    }

    public int getOrder_bus() {
        return order_bus;
    }

    public void setOrder_bus(int order_bus) {
        this.order_bus = order_bus;
    }

    public int getOrder_user() {
        return order_user;
    }

    public void setOrder_user(int order_user) {
        this.order_user = order_user;
    }

    public double getOrder_price() {
        return order_price;
    }

    public void setOrder_price(double order_price) {
        this.order_price = order_price;
    }

    public List<Gwc> getOrder_food() {
        return order_food;
    }

    public void setOrder_food(List<Gwc> order_food) {
        this.order_food = order_food;
    }
}
