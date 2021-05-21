package com.springboot.mapper;

import com.springboot.entity.Business;
import com.springboot.entity.EvaluateModel;
import com.springboot.entity.Food;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface MainMapper {

    @Select("SELECT\n" +
            "\tf.f_id,\n" +
            "\tf.f_name,\n" +
            "\tb.b_name,\n" +
            "  f.f_b_id,\n" +
            "\tf.f_sales_volume,\n" +
            "\tf.f_price,\n" +
            "  f_pic,\n" +
            "\tf_introduce,\n" +
            "  f_stock\n" +
            "FROM\n" +
            "\tt_business b,\n" +
            "\tt_food f\n" +
            "WHERE\n" +
            "\tf.f_b_id = b.b_id")
    ArrayList<Food> allfood();

    @Select("select * from t_business")
    ArrayList<Business> allbusiness();

    @Select("select * from t_food order by t_food.f_sales_volume DESC LIMIT 0,5")
    ArrayList<Food> top3food();

    @Select("select * from t_business where b_id = #{id}")
    Business getshop(int id);

    @Select("select t_food.f_b_id,t_food.f_id,t_food.f_introduce,t_food.f_name,t_food.f_pic,t_food.f_price,t_food.f_sales_volume,t_food.f_state,t_food.f_stock,t_business.b_name from t_food,t_business where t_food.f_b_id = #{shopid} and t_business.b_id = f_b_id")
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
            "AND e.e_food_id = f.f_id\n" +
            "AND b.b_id = e.e_b_id\n" +
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
            "AND e.e_food_id = f.f_id\n" +
            "AND b.b_id = e.e_b_id\n" +
            "AND f.f_id = #{foodid}\n" +
            "ORDER BY\n" +
            "\te.e_date\n")
    ArrayList<EvaluateModel> evaluateByfood(int foodid);

    @Select("SELECT\n" +
            "\tf.f_id,\n" +
            "\tf.f_name,\n" +
            "\tb.b_name,\n" +
            "  f.f_b_id,\n" +
            "\tf.f_sales_volume,\n" +
            "\tf.f_price,\n" +
            "  f_pic,\n" +
            "\tf_introduce,\n" +
            "  f_stock\n" +
            "FROM\n" +
            "\tt_business b,\n" +
            "\tt_food f\n" +
            "WHERE\n" +
            "\tf.f_b_id = b.b_id\n" +
            "  and f.f_id = #{foodid}")
    Food getFood(int foodid);

    @Select("select * from t_business order by b_monthly_sales LIMIT 0,10")
    ArrayList<Business> otherShop();

    @Select("SELECT\n" +
            "\tf.f_id,\n" +
            "\tf.f_name,\n" +
            "\tb.b_name,\n" +
            "  f.f_b_id,\n" +
            "\tf.f_sales_volume,\n" +
            "\tf.f_price,\n" +
            "  f_pic,\n" +
            "\tf_introduce,\n" +
            "  f_stock\n" +
            "FROM\n" +
            "\tt_business b,\n" +
            "\tt_food f\n" +
            "WHERE\n" +
            "\tf.f_b_id = b.b_id ORDER BY ${paixu} DESC LIMIT #{start}, #{end}")
    ArrayList<Food> allfoodByPage(@Param("paixu") String paixu,@Param("start") Integer start, @Param("end") Integer end);

    @Select("select * from t_food where f_name like CONCAT('%',#{search_s},'%') and f_state != 0 order by f_sales_volume")
    ArrayList<Food> searchFood(String search_s);

    @Select("select * from t_business ORDER BY ${paixu} DESC LIMIT #{start}, #{end}")
    ArrayList<Business> allbusinessByPage(@Param("paixu")String paixu,@Param("start") Integer start,@Param("end") Integer end);

    @Select("select * from t_business where b_name like CONCAT('%',#{keyword},'%') and b_state != 0 order by b_monthly_sales")
    ArrayList<Business> searchBus(String keyword);


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
            "AND e.e_food_id = f.f_id\n" +
            "AND e.e_b_id = b.b_id\n" +
            "AND b.b_id = #{shopid}\n" +
            "ORDER BY\n" +
            "\te.e_date\n")
    ArrayList<EvaluateModel> evaluateByshop(int shopid);
}
