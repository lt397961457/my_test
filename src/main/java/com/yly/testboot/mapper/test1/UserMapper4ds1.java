package com.yly.testboot.mapper.test1;

import com.yly.testboot.entity.UserEntity;

import java.util.List;

public interface UserMapper4ds1 {
    public UserEntity getOne(Integer id);
    public List<UserEntity> getAll();
}
