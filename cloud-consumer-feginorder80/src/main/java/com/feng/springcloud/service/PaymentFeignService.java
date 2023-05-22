package com.feng.springcloud.service;

import com.feng.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-PAYMENT")
public interface PaymentFeignService {
    @GetMapping(value = "/payment/get/{id}")
     CommonResult getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/apyemnt/timeout")
    public String paymentFeaginTimeout();
}
