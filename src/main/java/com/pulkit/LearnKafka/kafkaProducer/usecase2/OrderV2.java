package com.pulkit.LearnKafka.kafkaProducer.usecase2;

import lombok.Data;

@Data
public class OrderV2 {

    private String orderId;
    private String customerId;
    private String productId;
    private Integer quantity;
    private Double totalAmount;
    private String status;

    // getters and setters
}
