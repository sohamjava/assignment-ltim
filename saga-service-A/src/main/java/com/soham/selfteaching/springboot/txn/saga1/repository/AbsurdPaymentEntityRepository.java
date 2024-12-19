package com.soham.selfteaching.springboot.txn.saga1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soham.selfteaching.springboot.txn.saga1.entity.AbsurdPaymentEntity;

public interface AbsurdPaymentEntityRepository extends JpaRepository<AbsurdPaymentEntity, Long>{

}
