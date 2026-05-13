package com.pulkit.LearnKafka.kafkaProducer.usecase2;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.common.serialization.Serializer;
import tools.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.Map;

public class OrderSummarySerializer implements Serializer<OrderV2> {

    private final ObjectMapper objectMapper;

    public OrderSummarySerializer() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public byte[] serialize(String topic, OrderV2 order) {

        if (order == null) {
            return null;
        }

        // Build a map with only the fields we want to expose,
        // rest I removed it
        Map<String, Object> summary = new LinkedHashMap<>();

        summary.put("orderId", order.getOrderId());
        summary.put("productId", order.getProductId());

        return objectMapper.writeValueAsBytes(summary);

    }
}
