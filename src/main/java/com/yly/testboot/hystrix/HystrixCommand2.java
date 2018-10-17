package com.yly.testboot.hystrix;

import com.netflix.hystrix.*;

public class HystrixCommand2 extends HystrixCommand<String> {
    private final String name;
    private int sleepTime = 0;
    public HystrixCommand2(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("TestGroup2"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("Test2"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(500))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("TestPoolKey1"))
        );
        this.name = name;
    }

    @Override
    protected String getFallback() {
        return name + ":this fallBack return";
    }

    @Override
    protected String run() throws Exception {
        HystrixCommandKey commandKey =getCommandKey();
        HystrixCommandGroupKey commandGroupKey = getCommandGroup();
        HystrixThreadPoolKey poolKey = getThreadPoolKey();
        System.out.println(String.format("commandKey is ：%s，groupKey is : %s, poolKey is : %s",commandKey,commandGroupKey,poolKey));
        Thread.sleep(sleepTime);
        return name + ":this is run method return";
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }
}
