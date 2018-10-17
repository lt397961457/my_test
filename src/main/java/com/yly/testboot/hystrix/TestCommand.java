package com.yly.testboot.hystrix;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestCommand {

    /**
     * 同步Command测试
     */
    @Test
    public void syncCommandTest(){
        HelloCommand command = new HelloCommand("tom");
        String result = command.execute(); //每个command只能执行一次
        System.out.println(result);
    }

    /**
     * 异步command测试
     */
    @Test
    public void asyncCommandTest() throws ExecutionException, InterruptedException, TimeoutException {
        HelloCommand command = new HelloCommand("Async-tom");
        System.out.println("主线程====0");
        Future<String> f = command.queue();
        System.out.println("主线程====1");
        String result = f.get(1000, TimeUnit.MILLISECONDS); //可以不传超时时间默认是1S
        System.out.println("主线程====2");
        System.out.println(result);

    }

    /**
     * fallBack 测试
     */
    @Test
    public void testFallBack_1(){
        HystrixCommand2 fallbackCommand = new HystrixCommand2("with fall back");
        fallbackCommand.setSleepTime(500); //设置run方法执行时候的睡眠时间，用于触发超时机制
        String result = fallbackCommand.execute();
        System.out.println(result);
    }

}
