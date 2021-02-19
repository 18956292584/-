package com.springboot.entity;

import java.util.ArrayList;
import java.util.List;

public class OrderModel {
    int order_id;
    int order_distribut;
    String order_address;
    String order_message;
    double order_price;
    List<Gwc> order_food;

    public OrderModel(){

        order_food = new ArrayList<>();
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getOrder_distribut() {
        return order_distribut;
    }

    public void setOrder_distribut(int order_distribut) {
        this.order_distribut = order_distribut;
    }

    public String getOrder_address() {
        return order_address;
    }

    public void setOrder_address(String order_address) {
        this.order_address = order_address;
    }

    public String getOrder_message() {
        return order_message;
    }

    public void setOrder_message(String order_message) {
        this.order_message = order_message;
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
