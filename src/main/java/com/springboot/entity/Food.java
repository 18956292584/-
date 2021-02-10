package com.springboot.entity;

import lombok.Data;


//食物模板
@Data
public class Food {
    //食物id
    private String f_id;
    //食物名称
    private String f_name;
    //食物库存
    private int f_stock;
    //食物售量
    private int f_sales_volume;
    //食物对应的商家id
    private String f_b_id;
    //食物状态
    private boolean f_state;
    //食物说明
    private String f_introduce;
    //食物图片
    private String f_pic;
}
