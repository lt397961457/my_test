package com.yly.testboot.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class HelloCommand extends HystrixCommand<String> {
    private final String name;
    protected HelloCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("TestGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        System.out.println("进入了 command 的run方法");
        return "Hello " + name +"!Thread name is " +Thread.currentThread().getName();
    }

}
