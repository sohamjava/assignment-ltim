package com.soham.selfteaching.springboot.txn.saga1.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Service;

import com.soham.selfteaching.springboot.txn.saga1.entity.AbsurdPaymentEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service

@Slf4j
@RequiredArgsConstructor
public class KafkaService {
	private static final String TOPIC_PAYMENT_CREATED = "payment-created-topic";
	private static final String TOPIC_PAYMENT_REVERT = "payment-revert-topic";;
	private final KafkaTemplate<String, AbsurdPaymentEntity> kafkaTemplate;
	
	
	
	@Autowired
	@Lazy
	private AbsurdPaymentEntityService entityService;

	public CompletableFuture<SendResult<String, AbsurdPaymentEntity>> publishEvent(AbsurdPaymentEntity e) {
		ProducerRecord<String, AbsurdPaymentEntity> p = new ProducerRecord<>(TOPIC_PAYMENT_CREATED, e);
		return kafkaTemplate.send(p);
	}

	@Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ConsumerFactory<String, AbsurdPaymentEntity>  consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "pqs");
        JsonDeserializer<AbsurdPaymentEntity> des=new JsonDeserializer<>(AbsurdPaymentEntity.class, false);
        des.addTrustedPackages("com.soham.selfteaching.springboot.txn.saga1.entity");
		ConsumerFactory<String, AbsurdPaymentEntity> d= new DefaultKafkaConsumerFactory<>(props,new StringDeserializer(), des);
        
        return d;
    }

    @Bean(name = "kafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, AbsurdPaymentEntity > kafkaListenerContainerFactory() {

       ConcurrentKafkaListenerContainerFactory<String, AbsurdPaymentEntity > factory = new ConcurrentKafkaListenerContainerFactory<>();

       
       factory.setConsumerFactory(consumerFactory());
       

       return factory;
   }
	
	
	
	@KafkaListener(topics = { TOPIC_PAYMENT_REVERT })
	public void listen(AbsurdPaymentEntity en) {
		log.warn("Need to revert back {}: " + en);
		entityService.remove(en.getId());
		log.info("Deleted : {}",en);

	}

}
