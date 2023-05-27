package com.feng.springcloud.controller;

import com.feng.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
//设置全局降级方法
@DefaultProperties(defaultFallback = "payment_Global_TimeooutFallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") int id){
     return    paymentHystrixService.paymentInfo_OK(id);
    }

    @GetMapping("/consumer/payment/hystrix/time/out/{id}")
//    @HystrixCommand(fallbackMethod = "paymentTimeoutFallBackMethod" ,commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds" ,value = "1500")
//    })
    @HystrixCommand //使用全局fallback
    public String paymentInfo_timeout(@PathVariable("id") int id){
        int a=10/0;
        return  paymentHystrixService.paymentInfo_TimeOut(id);
    }

    public String paymentTimeoutFallBackMethod(@PathVariable("id") int id){
        return "这是消费者80，对方系统繁忙请等10秒再试，或检查自身";
    }
    public String payment_Global_TimeooutFallbackMethod(){
        return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }
}
