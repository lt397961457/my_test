package com.yly.testboot.controller;

import com.yly.testboot.entity.UserEntity;
import com.yly.testboot.mapper.test1.UserMapper4ds1;
import com.yly.testboot.mapper.test2.UserMapper4ds2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/mybatis/multidousce")
public class MybatisMultiSourceController {
    @Autowired
    private UserMapper4ds2 userMapper4ds2;
    @Autowired
    private UserMapper4ds1 userMapper4ds1;

    @GetMapping("/ds1/{id}")
    public UserEntity getOne(@PathVariable(value = "id") Integer userID){
        return userMapper4ds1.getOne(userID);
    }
    @GetMapping("/ds1/getall")
    public List<UserEntity> getAll(){
        return userMapper4ds1.getAll();
    }

    @GetMapping("/ds2/{id}")
    public UserEntity getOne2(@PathVariable(value = "id") Integer userID){
        return userMapper4ds2.getOne(userID);
    }
    @GetMapping("/ds2/getall")
    public List<UserEntity> getAll2(){
        return userMapper4ds2.getAll();
    }
}
