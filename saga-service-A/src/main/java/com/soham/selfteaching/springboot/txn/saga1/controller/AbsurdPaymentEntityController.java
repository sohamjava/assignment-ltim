package com.soham.selfteaching.springboot.txn.saga1.controller;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soham.selfteaching.springboot.txn.saga1.entity.AbsurdPaymentEntity;
import com.soham.selfteaching.springboot.txn.saga1.service.AbsurdPaymentEntityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payment")

@RequiredArgsConstructor
public class AbsurdPaymentEntityController {

	private final AbsurdPaymentEntityService service;
	private final Random random = new Random();

	@PostMapping
	public AbsurdPaymentEntity save() {
		String randomName = "Payment-" + random.nextInt(1, 20);
		AbsurdPaymentEntity entity = AbsurdPaymentEntity.builder().name(randomName).ts(LocalDateTime.now()).build();
		return service.save(entity);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") long id) {

		service.remove(id);
	}
}
