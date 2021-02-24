package com.springboot.entity;

import lombok.Data;

@Data
public class Evaluate {
    //评价id
    private int e_id;
    //订单id
    private String e_order_id;
    //用户id
    private String e_user_id;
    //食物id
    private int e_food_id;
    //评价
    private String e_evaluate;
    //评价时间
    private String e_date;
}
