package com.zekelabs.microserviceskafka;

public class IKafkaConstants {
    public static final String KAFKA_BROKERS = "localhost:9092";
	
	public static final Integer MESSAGE_COUNT=100;
	
	public static final String CLIENT_ID="client1";
	
	public static final String TOPIC_NAME="demo";
	
	public static final String SALES_TOPIC_UK="UK";
	public static final String SALES_TOPIC_FR="FR";
	
	public static final String GROUP_ID_CONFIG_UK="consumerGroup10";
	public static final String GROUP_ID_CONFIG_FR="consumerGroup11";
	
	public static final Integer MAX_NO_MESSAGE_FOUND_COUNT=100;
	
	public static final String OFFSET_RESET_LATEST="latest";
	
	public static final String OFFSET_RESET_EARLIER="earliest";
	
	public static final Integer MAX_POLL_RECORDS=1;

}
