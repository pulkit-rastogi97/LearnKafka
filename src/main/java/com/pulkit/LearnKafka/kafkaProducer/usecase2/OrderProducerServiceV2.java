package com.pulkit.LearnKafka.kafkaProducer.usecase2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class OrderProducerServiceV2 {

    @Autowired
    private KafkaTemplate<String, OrderV2> kafkaTemplate;

    public void sendWithoutKey(OrderV2 order) {
        CompletableFuture<SendResult<String, OrderV2>> future = kafkaTemplate.send("order-events", order);

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
