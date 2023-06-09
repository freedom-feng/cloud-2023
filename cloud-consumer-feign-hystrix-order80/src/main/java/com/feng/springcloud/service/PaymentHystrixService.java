package com.feng.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "cloud-provider-hystrix-payment",fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public  String paymentInfo_OK(@PathVariable("id") int id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String  paymentInfo_TimeOut(@PathVariable("id") int id);
}
