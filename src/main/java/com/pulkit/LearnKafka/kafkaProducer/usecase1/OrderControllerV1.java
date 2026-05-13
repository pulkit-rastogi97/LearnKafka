package com.pulkit.LearnKafka.kafkaProducer.usecase1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderControllerV1 {

    @Autowired
    OrderProducerServiceV1 orderProducerServiceV1;

    @PostMapping("/with-key")
    public ResponseEntity<String> sendWithKey(@RequestBody OrderV1 order) {
        orderProducerServiceV1.sendWithKey(order);
        return ResponseEntity.accepted().body("OrderV2 created and event published!");
    }
}
