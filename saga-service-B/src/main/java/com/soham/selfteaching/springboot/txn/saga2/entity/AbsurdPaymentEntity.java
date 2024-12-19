package com.soham.selfteaching.springboot.txn.saga2.entity;

import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AbsurdPaymentEntity {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	
	
	private String name;
	
	
	private LocalDateTime ts;
	
}
