package nl.kooi.kafka.tutorial1;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemoWithCallback {

    public static void main(String[] args)  {

        final Logger logger = LoggerFactory.getLogger(ProducerDemoWithCallback.class.getName());
        //        create Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //        create the Producer
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        for (int i=0;i<10; i++) {
            //        create producer record
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("first_topic", "hello world" + i);

            //        send data -asynchronous
            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
//                executes every time a record is successfully sent or an exception is thrown
                    if (e == null) {

                        logger.info("Receiver new MetData. \n" +
                                "Topic:" + recordMetadata.topic() + "\n" +
                                "Partition:" + recordMetadata.partition() + "\n" +
                                "Offset:" + recordMetadata.offset() + "\n" +
                                "Timestamp:" + recordMetadata.timestamp());

                    } else {
                        logger.info("Error while producing: " + e.getMessage());
                    }
                }
            });
        }

//        flush data
        producer.flush();

//        flush and close producer
        producer.close();

    }
}
