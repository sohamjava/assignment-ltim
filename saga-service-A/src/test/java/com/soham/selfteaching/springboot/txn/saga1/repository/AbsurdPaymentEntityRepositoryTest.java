package com.soham.selfteaching.springboot.txn.saga1.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import com.soham.selfteaching.springboot.txn.saga1.entity.AbsurdPaymentEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.yml")
class AbsurdPaymentEntityRepositoryTest {
	@Autowired
	private AbsurdPaymentEntityRepository absurdPaymentEntityRepository;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Repository test")
	void add() {
		AbsurdPaymentEntity absurdPaymentEntity = AbsurdPaymentEntity.builder().name("testTxn").ts(LocalDateTime.now())
				.build();
		absurdPaymentEntity=absurdPaymentEntityRepository.save(absurdPaymentEntity);
		assertNotNull(absurdPaymentEntity.getId());
		
		assertThat(absurdPaymentEntityRepository.findById(absurdPaymentEntity.getId()).get().getName()).isEqualTo(absurdPaymentEntity.getName());
		absurdPaymentEntityRepository.deleteById(absurdPaymentEntity.getId());
		assertThat(absurdPaymentEntityRepository.findById(absurdPaymentEntity.getId()).isEmpty()).isEqualTo(true);
	}

}
