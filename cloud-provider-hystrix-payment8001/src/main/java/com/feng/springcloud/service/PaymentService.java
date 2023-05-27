package com.feng.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_OK(int id){
        return  "线程池:   " + Thread.currentThread().getName()+"   paymentInfo_OK:  "+id+" it`s all right";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String PaymentInfo_TimeOut(int id){
        int timeNumber=5;
        //int a=10/0;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return  "线程池:   " + Thread.currentThread().getName()+"   PaymentInfo_TimeOut:  "+id+" it`s all right"+"耗时： "+timeNumber;
    }

    public String paymentInfo_TimeoutHandler(int id){
        return  "线程池:   " + Thread.currentThread().getName()+"   PaymentInfo_TimeOut:  "+id+" 启用备用方案";
    }

    @HystrixCommand(fallbackMethod = "paymentCircuitBreake_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), //是否开启短路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage" ,value = "60") //失败率
            //在时间窗规定的时间和请求次数内，失败率达到设定的失败率，就熔断
    })
    public String paymentCircuitBreake(@PathVariable("id") Integer id){
        if(id<0){
            throw  new RuntimeException("******id 不能负数");
        }
        String serialNuimber= IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNuimber;
    }


    public String paymentCircuitBreake_fallback(@PathVariable("id") Integer id){
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }
}
