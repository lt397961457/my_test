package com.yly.testboot.jsp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class JspBean {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public String metod1(String args){
        logger.info("metod1 的 args:"+args);
        return "args" + args;
    }

    public String[] metod2(List<String> args){

        for(String s : args){
            logger.info("metod2 的 args:"+args);

        }
        return args.toArray(new String[args.size()]);
    }
}
