package com.pulkit.LearnKafka.kafkaProducer.usecase1;

import jdk.jfr.DataAmount;
import lombok.Data;

@Data
public class OrderV1 {

    private String orderId;
    private String customerId;
    private String productId;
    private Integer quantity;
    private Double totalAmount;
    private String status;

    // getters and setters
}
