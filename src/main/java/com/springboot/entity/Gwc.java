package com.springboot.entity;

import lombok.Data;

@Data
public class Gwc {
    private int	gwc_id;
    private int	cust_id;
    private int	food_id;
    private int	bus_id;
    private String	food_pic;
    private double	food_price;
    private int	food_count;
    private String	food_name;
    private String	bus_name;
    private boolean gwc_state;

    @Override
    public String toString() {
        return "Gwc{" +
                "gwc_id=" + gwc_id +
                ", cust_id=" + cust_id +
                ", food_id=" + food_id +
                ", bus_id=" + bus_id +
                ", food_pic='" + food_pic + '\'' +
                ", food_price=" + food_price +
                ", food_count=" + food_count +
                ", food_name='" + food_name + '\'' +
                ", bus_name='" + bus_name + '\'' +
                ", gwc_state=" + gwc_state +
                '}';
    }

    public boolean isGwc_state() {
        return gwc_state;
    }

    public void setGwc_state(boolean gwc_state) {
        this.gwc_state = gwc_state;
    }

    public int getGwc_id() {
        return gwc_id;
    }

    public void setGwc_id(int gwc_id) {
        this.gwc_id = gwc_id;
    }

    public int getCust_id() {
        return cust_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public int getBus_id() {
        return bus_id;
    }

    public void setBus_id(int bus_id) {
        this.bus_id = bus_id;
    }

    public String getFood_pic() {
        return food_pic;
    }

    public void setFood_pic(String food_pic) {
        this.food_pic = food_pic;
    }

    public double getFood_price() {
        return food_price;
    }

    public void setFood_price(double food_price) {
        this.food_price = food_price;
    }

    public int getFood_count() {
        return food_count;
    }

    public void setFood_count(int food_count) {
        this.food_count = food_count;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getBus_name() {
        return bus_name;
    }

    public void setBus_name(String bus_name) {
        this.bus_name = bus_name;
    }

}
