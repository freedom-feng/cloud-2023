package com.feng.springcloud.controller;

import com.feng.springcloud.entities.CommonResult;
import com.feng.springcloud.entities.payment;
import com.feng.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private  String serverPort;
    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody payment payment){
        int result =paymentService.create(payment);
        log.info("*********插入结果为： "+ result);
        if (result > 0){
            return  new CommonResult(200,"插入成功",result);
        }else {
            return  new CommonResult(400,"插入失败",null);
        }
    }
     @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        payment payment=paymentService.getPaymentById(id);
        log.info("*********查询结果为"+payment+"hahah");
        if(payment!=null){
            log.info("成功了");
            return  new CommonResult(200,"查询成功 port:8002",payment);
        }else {
            log.info("失败了");
            return  new CommonResult(400,"没有查到",null);
        }
    }
    @GetMapping(value = "/apyemnt/timeout")
    public String paymentFeaginTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return  serverPort;
    }

    @GetMapping("/payment/lb")
    public String paymentGetPort(){
        return  serverPort;
    }
}
