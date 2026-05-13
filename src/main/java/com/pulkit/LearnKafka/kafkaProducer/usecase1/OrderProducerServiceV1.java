package com.pulkit.LearnKafka.kafkaProducer.usecase1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class OrderProducerServiceV1 {

    @Autowired
    private KafkaTemplate<String, OrderV1> kafkaTemplate;

    public void sendWithKey(OrderV1 order) {
        String key = order.getOrderId();

        CompletableFuture<SendResult<String, OrderV1>> future = kafkaTemplate.send("order-events", key, order);

        future.whenComplete((result, ex) -> {

            if (ex == null) {

                System.out.println("OrderV2 event sent successfully");

                System.out.println("Partition: " + result.getRecordMetadata().partition());

                System.out.println("Offset: " + result.getRecordMetadata().offset());

            } else {

                System.out.println("Failed to send order event: " + ex.getMessage());
            }
        });
    }
}
