package com.feng.springcloud.controller;

import com.feng.springcloud.entities.CommonResult;
import com.feng.springcloud.entities.payment;
import com.feng.springcloud.servies.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private  String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/payment/discover")
    public Object Discover(){
        List<String> services = discoveryClient.getServices();
        for (String servicesName : services){
            log.info("******servicesName:"+servicesName);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-PAYMENT");
        for (ServiceInstance serviceInstance : instances){
            log.info(serviceInstance.getServiceId()+"    "+serviceInstance.getHost()+"     "+serviceInstance.getPort()+"    "+serviceInstance.getUri());
        }
        return this.discoveryClient;
    }
    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody payment payment){
        int result=paymentService.create(payment);
        log.info("*********插入结果为： "+ result);
        if(result > 0){
           return new CommonResult(200,"插入成功",result);
        }else {
            return  new CommonResult(400,"插入失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        payment payment= paymentService.getPaymentById(id);
        log.info("*********查询结果为"+payment+"hahah");
        if(payment!=null){
            return new CommonResult(200,"查询成功 port:8001",payment);
        }else {
            return new CommonResult(400,"查询失败",null);
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
    public  String paymentGetPort(){
        return serverPort;
    }
}
