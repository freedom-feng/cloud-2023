package com.feng.springcloud.servies.Impl;

import com.feng.springcloud.dao.PaymentDao;
import com.feng.springcloud.entities.payment;
import com.feng.springcloud.servies.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private  PaymentDao paymentDao;
    @Override
    public int create(payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
