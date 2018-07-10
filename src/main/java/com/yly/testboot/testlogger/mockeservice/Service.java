package com.yly.testboot.testlogger.mockeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Service {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public void test(){
        logger.info("这是一个测试Service测的日志");
    }
}
