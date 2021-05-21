package com.springboot.mapper;


import com.springboot.entity.Address;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AddressMapper {

    @Select("select * from t_address where a_user = #{userId} and a_state != 0")
    List<Address> getAddress(String userId);

    @Select("select * from t_address where a_id = #{order_address} and a_state != 0")
    Address getAddressByid(int order_address);

    @Insert("INSERT INTO t_address(a_phone,a_address,a_name,a_user,a_state)" +
            "VALUES(#{address.a_phone},#{address.a_address},#{address.a_name},#{address.a_user},1)")
    void addAddress(@Param("address")Address address);

    @Update("update t_address set a_phone = #{address.a_phone},a_address = #{address.a_address},a_name = #{address.a_name} where a_id = #{address.a_id}")
    void ChangeAddress(@Param("address")Address address);

    @Update("update t_address set a_state = 0 where a_id = #{address.a_id}")
    void DeletAddress(@Param("address")Address address);
}
