package com.yly.testboot.dao.mongo;

import com.yly.testboot.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring jpa 对mongo形式的dao
 */
public interface User4MongoRepository extends MongoRepository<UserEntity,Long> {
    UserEntity findByUserName(String userName);
    UserEntity findByPhoneNum(String phoneNum);
}
