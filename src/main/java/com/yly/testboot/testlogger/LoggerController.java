package com.yly.testboot.testlogger;

import com.yly.testboot.testlogger.mockeservice.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class LoggerController {
//    private final Logger logger = LoggerFactory.getLogger("controller");
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Service service;

    @GetMapping("/info")
    public String info(){
        logger.info("这是一个log INFO 级别的信息");
        service.test();
        return "info";
    }
    @GetMapping("/debug")
    public String debug(){
        logger.debug("这是一个log DEBUG 级别的信息");
        return "debug";
    }
    @GetMapping("/warn")
    public String warn(){
        logger.warn("这是一个log warn 级别的信息");
        return "warn";
    }

    @GetMapping("/error")
    public String error(){
        logger.error("这是一个log error 级别的信息");
        return "error";
    }
    @GetMapping("/trace")
    public String trace(){
        logger.trace("这是一个log error 级别的信息");
        return "trace";
    }
}
