package com.springboot.entity;


import lombok.Data;

//这是商家的model
@Data
public class Business {
    //商家id
    private int b_id;
    //商家名称
    private String b_name;
    //商家地址
    private String b_address;
    //商家电话
    private String b_phone;
    //商家特色
    private String b_special;
    //店铺介绍
    private String b_Introduction;
    //营业时间
    private String b_time;
    //商家评分
    private double b_score;
    //商家图片，log
    private String b_pic;
    //商家营业状态
    private boolean b_state;
    //商家月售量
    private int b_monthly_sales;
    //商家配送费
    private boolean b_dismoney;
    //商家登录密码
    private String b_password;


    public String getB_password() {
        return b_password;
    }

    public void setB_password(String b_password) {
        this.b_password = b_password;
    }

    public int getB_id() {
        return b_id;
    }

    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    public String getB_address() {
        return b_address;
    }

    public void setB_address(String b_address) {
        this.b_address = b_address;
    }

    public String getB_phone() {
        return b_phone;
    }

    public void setB_phone(String b_phone) {
        this.b_phone = b_phone;
    }

    public String getB_special() {
        return b_special;
    }

    public void setB_special(String b_special) {
        this.b_special = b_special;
    }

    public String getB_Introduction() {
        return b_Introduction;
    }

    public void setB_Introduction(String b_Introduction) {
        this.b_Introduction = b_Introduction;
    }

    public String getB_time() {
        return b_time;
    }

    public void setB_time(String b_time) {
        this.b_time = b_time;
    }

    public double getB_score() {
        return b_score;
    }

    public void setB_score(double b_score) {
        this.b_score = b_score;
    }

    public String getB_pic() {
        return b_pic;
    }

    public void setB_pic(String b_pic) {
        this.b_pic = b_pic;
    }

    public boolean isB_state() {
        return b_state;
    }

    public void setB_state(boolean b_state) {
        this.b_state = b_state;
    }

    public int getB_monthly_sales() {
        return b_monthly_sales;
    }

    public void setB_monthly_sales(int b_monthly_sales) {
        this.b_monthly_sales = b_monthly_sales;
    }

    public boolean isB_dismoney() {
        return b_dismoney;
    }

    public void setB_dismoney(boolean b_dismoney) {
        this.b_dismoney = b_dismoney;
    }
}
