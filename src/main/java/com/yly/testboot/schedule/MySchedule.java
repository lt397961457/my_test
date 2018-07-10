package com.yly.testboot.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MySchedule {
    private int count = 0;
    @Scheduled(cron="* * * * * ?")
    private void process(){
//        System.out.println("this is scheduler task runing  "+(count++));
    }
}
