package com.yly.testboot;

import com.yly.testboot.util.propertiesutil.MyProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableConfigurationProperties(MyProperties.class)
//配置Mysql 与 mongo 不同数据源 ，下面添加Repositories类扫描，两个数据源必须添加，
// 目前测试到的是 如果 mongo加入了依赖，同时有地方import，如果没有下面两个配置，那就会报错。
@EnableJpaRepositories(basePackages = "com.yly.testboot.dao.mysql")
@EnableMongoRepositories(basePackages = "com.yly.testboot.dao.mongo")
//mybatis 扫描Mapper的包路径
//配置多数据源以后 注释
//@MapperScan("com.yly.testboot.mapper")
public class TestBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestBootApplication.class, args);
	}
}

