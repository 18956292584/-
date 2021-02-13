package com.springboot.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class EvaluateModel {
    private String user_name;
    //用户id
    private String f_name;
    //食物id
    private String f_pic;
    //评价
    private String b_name;
    //评价时间
    private String e_evaluate;
    //评价时间
    private String e_date;
}
