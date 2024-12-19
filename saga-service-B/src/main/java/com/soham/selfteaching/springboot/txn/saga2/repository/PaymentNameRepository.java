package com.soham.selfteaching.springboot.txn.saga2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soham.selfteaching.springboot.txn.saga2.entity.PaymentName;

public interface PaymentNameRepository extends JpaRepository<PaymentName, Long>{

}
