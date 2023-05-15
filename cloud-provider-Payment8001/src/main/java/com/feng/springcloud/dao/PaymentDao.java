package com.feng.springcloud.dao;

import com.feng.springcloud.entities.payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    public int create(payment payment);
    public payment getPaymentById(@Param("id") Long id);
}
