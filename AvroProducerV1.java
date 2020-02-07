import java.util.*;
import org.apache.kafka.clients.producer.*;
public class AvroProducerV1 {

    public static void main(String[] args) throws Exception{

        String topicName = "AvroTransaction";
        String msg;

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092,localhost:9093");        
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        props.put("schema.registry.url", "http://localhost:8081");

        Producer<String, TransactionMessage> producer = new KafkaProducer <>(props);
        TransactionMessage tm = new TransactionMessage();
        try{
            tm.setDescription("P6092");
            tm.setQuantity("20");
            tm.setUnitPrice("127.25");

            producer.send(new ProducerRecord<String, TransactionMessage>(topicName,tm.getDescription().toString(),tm)).get();

            System.out.println("Complete");
        }
        catch(Exception ex){
            ex.printStackTrace(System.out);
        }
        finally{
            producer.close();
        }

   }
}
