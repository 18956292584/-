package com.springboot.mapper;


import com.springboot.entity.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AddressMapper {

    @Select("select * from t_address where a_user = #{userId} and a_state != 0")
    List<Address> getAddress(int userId);

    @Select("select * from t_address where a_id = #{order_address} and a_state != 0")
    Address getAddressByid(int order_address);
}
