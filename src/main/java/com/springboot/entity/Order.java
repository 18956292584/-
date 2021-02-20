package com.springboot.entity;

public class Order {
    String order_id;
    int order_address;
    String order_message;
    double order_price;
    int order_bus;
    int order_user;
    String order_date;
    boolean order_isOk;


    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public int getOrder_address() {
        return order_address;
    }

    public void setOrder_address(int order_address) {
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

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public boolean isOrder_isOk() {
        return order_isOk;
    }

    public void setOrder_isOk(boolean order_isOk) {
        this.order_isOk = order_isOk;
    }
}
