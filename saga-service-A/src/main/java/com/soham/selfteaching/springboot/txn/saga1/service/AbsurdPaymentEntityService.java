package com.soham.selfteaching.springboot.txn.saga1.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.soham.selfteaching.springboot.txn.saga1.entity.AbsurdPaymentEntity;
import com.soham.selfteaching.springboot.txn.saga1.repository.AbsurdPaymentEntityRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service

@Slf4j
@RequiredArgsConstructor
public class AbsurdPaymentEntityService {
	

	private final AbsurdPaymentEntityRepository absurdPaymentEntityRepository;
	private final KafkaService kafkaService;

	@Transactional(rollbackOn = Exception.class)
	public AbsurdPaymentEntity save(AbsurdPaymentEntity entity) {
		var e= absurdPaymentEntityRepository.save(entity);
		CompletableFuture<SendResult<String, AbsurdPaymentEntity>> publishEvent = kafkaService.publishEvent(entity);
		try {
			publishEvent.whenComplete((sr,ex)->{
				if(null!=ex) {
					throw new RuntimeException(ex);
				}
			}).get();
		} catch (InterruptedException | ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new RuntimeException(e1);
		}
		return e;
		
	}

	public void remove(Long id) {
		absurdPaymentEntityRepository.deleteById(id);
	}

}
