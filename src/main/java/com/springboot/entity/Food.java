package com.springboot.entity;

import lombok.Data;

//食物模板
@Data
public class Food {
    //食物id
    private int f_id;
    //食物名称
    private String f_name;
    //食物库存
    private int f_stock;
    //食物售量
    private int f_sales_volume;
    //食物对应的商家id
    private int f_b_id;
    //食物对应的商家名称
    private String b_name;
    //食物状态
    private boolean f_state;
    //食物说明
    private String f_introduce;
    //食物图片
    private String f_pic;
    //食物价格
    private double f_price;

    public int getF_id() {
        return f_id;
    }

    public void setF_id(int f_id) {
        this.f_id = f_id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public int getF_stock() {
        return f_stock;
    }

    public void setF_stock(int f_stock) {
        this.f_stock = f_stock;
    }

    public int getF_sales_volume() {
        return f_sales_volume;
    }

    public void setF_sales_volume(int f_sales_volume) {
        this.f_sales_volume = f_sales_volume;
    }

    public int getF_b_id() {
        return f_b_id;
    }

    public void setF_b_id(int f_b_id) {
        this.f_b_id = f_b_id;
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    public boolean isF_state() {
        return f_state;
    }

    public void setF_state(boolean f_state) {
        this.f_state = f_state;
    }

    public String getF_introduce() {
        return f_introduce;
    }

    public void setF_introduce(String f_introduce) {
        this.f_introduce = f_introduce;
    }

    public String getF_pic() {
        return f_pic;
    }

    public void setF_pic(String f_pic) {
        this.f_pic = f_pic;
    }

    public double getF_price() {
        return f_price;
    }

    public void setF_price(double f_price) {
        this.f_price = f_price;
    }
}
