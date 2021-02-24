package com.springboot.entity;

import java.util.ArrayList;
import java.util.List;

public class OrderModel {
    int order_bus;
    String order_busName;
    String order_user;
    String order_userName;
    Address address;
    Business business;
    double order_price;
    List<Gwc> order_food;
    Order order;

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderModel(){
        order_food = new ArrayList<>();
    }

    public String getOrder_busName() {
        return order_busName;
    }

    public void setOrder_busName(String order_busName) {
        this.order_busName = order_busName;
    }

    public String getOrder_userName() {
        return order_userName;
    }

    public void setOrder_userName(String order_userName) {
        this.order_userName = order_userName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getOrder_bus() {
        return order_bus;
    }

    public void setOrder_bus(int order_bus) {
        this.order_bus = order_bus;
    }

    public String getOrder_user() {
        return order_user;
    }

    public void setOrder_user(String order_user) {
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
