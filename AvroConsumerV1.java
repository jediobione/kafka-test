import java.util.*;
import org.apache.kafka.clients.consumer.*;


public class AvroConsumerV1{    
    
    public static void main(String[] args) throws Exception{

        String topicName = "AvroTransaction";
            
        String groupName = "RG";
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092,localhost:9093");
        props.put("group.id", groupName);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer");
        props.put("schema.registry.url", "http://localhost:8081");
        props.put("specific.avro.reader", "true");
        
        KafkaConsumer<String, TransactionMessage> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topicName));
        try{
            while (true){
                ConsumerRecords<String, TransactionMessage> records = consumer.poll(100);
                for (ConsumerRecord<String, TransactionMessage> record : records){
                        System.out.println("Description="+ record.value().getDescription()
                                         + " Quantity=" + record.value().getQuantity() 
                                         + " Unit Price=" + record.value().getUnitPrice());
                    }
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
            finally{
                consumer.close();
            }
    }
    
}
