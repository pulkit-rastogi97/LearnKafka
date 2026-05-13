package com.pulkit.LearnKafka.kafkaProducer.multipleSerializer;

import com.pulkit.LearnKafka.kafkaProducer.usecase1.OrderV1;
import com.pulkit.LearnKafka.kafkaProducer.usecase2.OrderV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class OrderProducerServiceV3 {

    //Template built from default application.properties for this profile.
    @Autowired
    private KafkaTemplate<String, OrderV1> kafkaTemplate;

    //Template built from custom configurations using @Bean class
    @Autowired
    @Qualifier("customSerializerKafkaTemplate")
    private KafkaTemplate<String, OrderV2> customSerializerKafkaTemplate;

    public void sendWithoutKey(OrderV2 order) {

        String key = order.getOrderId();

        CompletableFuture<SendResult<String, OrderV2>> future =
                customSerializerKafkaTemplate.send("order-events", order);

        future.whenComplete((result, ex) -> {

            if (ex == null) {

                System.out.println("Order event sent successfully");

                System.out.println(
                        "Partition: " +
                                result.getRecordMetadata().partition()
                );

                System.out.println(
                        "Offset: " +
                                result.getRecordMetadata().offset()
                );

            } else {

                System.out.println(
                        "Failed to send order event: " +
                                ex.getMessage()
                );
            }
        });
    }
}
