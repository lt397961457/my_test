package com.yly.testboot.mapper.test;

import com.yly.testboot.entity.UserEntity;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface UserMapper {
    @Select("SELECT * FROM table_user WHERE id = #{id}")
    @Results(id="userMap",value = {
            @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
//            @Result(column="age ", property="age", jdbcType=JdbcType.INTEGER),
            @Result(column="phone_num", property="phoneNum", jdbcType=JdbcType.VARCHAR)
    })
    UserEntity getOne(Integer id);

    @Select("SELECT * FROM table_user")
    @ResultMap(value = "userMap")
    List<UserEntity> getAll();
}
