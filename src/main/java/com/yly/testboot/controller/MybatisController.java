package com.yly.testboot.controller;

import com.yly.testboot.entity.UserEntity;
import com.yly.testboot.mapper.test.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/mybatis")
public class MybatisController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/{id}")
    public UserEntity getByUserID(@PathVariable(value = "id") Integer userID){
        return userMapper.getOne(userID);
    }
    @GetMapping("/allUser")
    public List<UserEntity> getAllUser(){
        return userMapper.getAll();
    }


}
