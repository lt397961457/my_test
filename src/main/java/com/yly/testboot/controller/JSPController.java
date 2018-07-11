package com.yly.testboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * ClassName:StudentController 
 * Date:     2017年11月6日 下午4:27:40
 * @author   Joe  
 * @version    
 * @since    JDK 1.8
 */
@Controller
@RequestMapping("/jsp")
public class JSPController {

    /**
     * view:(跳转到JSP界面).  
     * @author Joe
     * Date:2017年11月6日下午4:29:27
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/view",method = RequestMethod.GET)
    public String view(Map<String, Object> map) {
        map.put("name", "SpringBoot");
        map.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return "first";
    }
}