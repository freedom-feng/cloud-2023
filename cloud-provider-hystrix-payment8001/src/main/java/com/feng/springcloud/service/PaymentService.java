package com.feng.springcloud.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_OK(int id){
        return  "线程池:   " + Thread.currentThread().getName()+"   paymentInfo_OK:  "+id+" it`s all right";
    }

    public String PaymentInfo_TimeOut(int id){
        int timeNumber=3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return  "线程池:   " + Thread.currentThread().getName()+"   PaymentInfo_TimeOut:  "+id+" it`s all right"+"耗时： "+timeNumber;
    }
}
