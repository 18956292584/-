package com.springboot.mapper;

import com.springboot.entity.Business;
import com.springboot.entity.Evaluate;
import com.springboot.entity.EvaluateModel;
import com.springboot.entity.Food;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface MainMapper {

    @Select("select * from t_food")
    ArrayList<Food> allfood();

    @Select("select * from t_business")
    ArrayList<Business> allbusiness();

    @Select("select * from t_food order by t_food.f_sales_volume LIMIT 0,10")
    ArrayList<Food> top3food();

    @Select("select * from t_business where b_id = #{id}")
    Business getshop(int id);

    @Select("select * from t_food where f_b_id = #{shopid}")
    ArrayList<Food> shopFood(int shopid);

    @Select("select * from t_business where b_id != #{shopid} order by b_monthly_sales LIMIT 0,10")
    ArrayList<Business> othershop(int shopid);

    @Select("SELECT\n" +
            "\tu.user_name,\n" +
            "\tf.f_name,\n" +
            "\tf.f_pic,\n" +
            "\tb.b_name,\n" +
            "\te.e_evaluate\n" +
            "FROM\n" +
            "\tt_evaluate e,\n" +
            "\tt_business b,\n" +
            "\t`user` u,\n" +
            "\tt_food f\n" +
            "WHERE\n" +
            "\tu.user_id = e.e_user_id\n" +
            "AND e.e_food_id = f.f_b_id\n" +
            "AND b.b_id = e.e_id\n" +
            "ORDER BY\n" +
            "\te.e_date\n" +
            "LIMIT 10;")
    ArrayList<EvaluateModel> top10evaluate();

    @Select("SELECT\n" +
            "\tu.user_name,\n" +
            "\tf.f_name,\n" +
            "\tf.f_pic,\n" +
            "\tb.b_name,\n" +
            "\te.e_evaluate,\n" +
            "\te.e_date\n" +
            "FROM\n" +
            "\tt_evaluate e,\n" +
            "\tt_business b,\n" +
            "\t`user` u,\n" +
            "\tt_food f\n" +
            "WHERE\n" +
            "\tu.user_id = e.e_user_id\n" +
            "AND e.e_food_id = f.f_b_id\n" +
            "AND b.b_id = e.e_id\n" +
            "AND b.b_id = #{shopid}\n" +
            "ORDER BY\n" +
            "\te.e_date\n" +
            "LIMIT 100;")
    ArrayList<EvaluateModel> evaluateByshop(int shopid);

    @Select("select * from t_food where f_id = #{foodid}")
    Food getFood(int foodid);
}
