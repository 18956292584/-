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
    //用户id
    private String e_b_id;
    //食物id
    private int e_food_id;
    //评价
    private String e_evaluate;
    //评价时间
    private String e_date;

    public String getE_b_id() {
        return e_b_id;
    }

    public void setE_b_id(String e_b_id) {
        this.e_b_id = e_b_id;
    }

    @Override
    public String toString() {
        return "Evaluate{" +
                "e_id=" + e_id +
                ", e_order_id='" + e_order_id + '\'' +
                ", e_user_id='" + e_user_id + '\'' +
                ", e_food_id=" + e_food_id +
                ", e_evaluate='" + e_evaluate + '\'' +
                ", e_date='" + e_date + '\'' +
                '}';
    }

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }

    public String getE_order_id() {
        return e_order_id;
    }

    public void setE_order_id(String e_order_id) {
        this.e_order_id = e_order_id;
    }

    public String getE_user_id() {
        return e_user_id;
    }

    public void setE_user_id(String e_user_id) {
        this.e_user_id = e_user_id;
    }

    public int getE_food_id() {
        return e_food_id;
    }

    public void setE_food_id(int e_food_id) {
        this.e_food_id = e_food_id;
    }

    public String getE_evaluate() {
        return e_evaluate;
    }

    public void setE_evaluate(String e_evaluate) {
        this.e_evaluate = e_evaluate;
    }

    public String getE_date() {
        return e_date;
    }

    public void setE_date(String e_date) {
        this.e_date = e_date;
    }
}
