package com.soham.selfteaching.springboot.txn.saga2.service;

import org.springframework.stereotype.Service;

import com.soham.selfteaching.springboot.txn.saga2.entity.AbsurdPaymentEntity;
import com.soham.selfteaching.springboot.txn.saga2.entity.PaymentName;
import com.soham.selfteaching.springboot.txn.saga2.repository.PaymentNameRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service

@Slf4j
@RequiredArgsConstructor
public class PaymentNameService {

	private final PaymentNameRepository repository;
	

	public AbsurdPaymentEntity save(AbsurdPaymentEntity entity) {

		var e = repository.save(PaymentName.builder().name(entity.getName()).build());
		log.info("Saved : {}", entity.getName());
		return entity;

	}

	public void remove(Long id) {
		repository.deleteById(id);
	}

}
