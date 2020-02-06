package com.zekelabs.microserviceskafka;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zekelabs.microserviceskafka.pojo.SalesData;
import com.zekelabs.microserviceskafka.util.SalesStore;



@SpringBootApplication
@EnableEurekaClient
public class MicroServicesConsumerFRApplication {
	
	private final Logger logger = LoggerFactory.getLogger(MicroServicesConsumerFRApplication.class);

	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static void main(String[] args) {
		SpringApplication.run(MicroServicesConsumerFRApplication.class, args);
	}
	
	/*
	 * @LoadBalanced
	 * 
	 * @Bean 
	 * public RestTemplate getRestTemplate() { return new RestTemplate(); }
	 */
	
	/*
	 * @Bean public ConcurrentKafkaListenerContainerFactory<?, ?>
	 * kafkaListenerContainerFactory(
	 * ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
	 * ConsumerFactory<Object, Object> kafkaConsumerFactory, KafkaTemplate<Object,
	 * Object> template) { ConcurrentKafkaListenerContainerFactory<Object, Object>
	 * factory = new ConcurrentKafkaListenerContainerFactory<>();
	 * configurer.configure(factory, kafkaConsumerFactory);
	 * factory.setErrorHandler(new SeekToCurrentErrorHandler( new
	 * DeadLetterPublishingRecoverer(template), 3)); // dead-letter after 3 tries
	 * return factory; }
	 */

	
	@KafkaListener(id = IKafkaConstants.GROUP_ID_CONFIG_FR, topics = IKafkaConstants.SALES_TOPIC_FR)
	public void listen(SalesData sales) {
		try {
			logger.info("Received: " + objectMapper.writeValueAsString(sales));
			SalesStore.getSalesFR().put(sales.getStockCode(), sales.getQuantity()*sales.getUnitPrice());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	

}


