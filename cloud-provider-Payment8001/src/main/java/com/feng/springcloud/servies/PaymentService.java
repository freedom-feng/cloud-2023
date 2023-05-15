package com.feng.springcloud.servies;

import com.feng.springcloud.entities.payment;

public interface PaymentService {
    public int create(payment payment);
    public payment getPaymentById(Long id);
}
