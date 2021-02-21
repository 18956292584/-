package com.springboot.mapper;

import com.springboot.entity.Business;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BusinessMapper {


    @Select("select * from t_business where b_id = #{order_bus}")
    Business getBus(int order_bus);
}
