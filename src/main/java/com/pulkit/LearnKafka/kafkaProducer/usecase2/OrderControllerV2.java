package com.pulkit.LearnKafka.kafkaProducer.usecase2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderControllerV2 {

    @Autowired
    OrderProducerServiceV2 orderProducerService;

    @PostMapping("/with-no-key")
    public ResponseEntity<String> sendWithKey(@RequestBody OrderV2 order) {
        orderProducerService.sendWithoutKey(order);
        return ResponseEntity.accepted().body("OrderV2 created and event published!");
    }
}
