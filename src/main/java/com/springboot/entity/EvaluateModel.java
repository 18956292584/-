package com.springboot.entity;

import lombok.Data;

@Data
public class EvaluateModel {
    //用户名称
    private String user_name;
   //食物名称
    private String f_name;
    //食物照片
    private String f_pic;
    //商家名称
    private String b_name;
    //评价
    private String e_evaluate;
    //评价时间
    private String e_date;
}
