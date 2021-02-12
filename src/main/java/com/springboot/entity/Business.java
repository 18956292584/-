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
    //商家营业状态
    private int b_monthly_sales;

}
