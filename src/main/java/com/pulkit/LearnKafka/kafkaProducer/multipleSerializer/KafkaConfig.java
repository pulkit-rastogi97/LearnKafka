package com.pulkit.LearnKafka.kafkaProducer.multipleSerializer;

import com.pulkit.LearnKafka.kafkaProducer.usecase2.OrderSummarySerializer;
import com.pulkit.LearnKafka.kafkaProducer.usecase2.OrderV2;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.kafka.autoconfigure.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean
    public KafkaTemplate<String, OrderV2> customSerializerKafkaTemplate(
            KafkaProperties kafkaProperties) {

        // Start with all producer properties from application.properties
        Map<String, Object> props =
                new HashMap<>(kafkaProperties.buildProducerProperties());

        // Override ONLY the value serializer - everything else stays the same
        props.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                OrderSummarySerializer.class
        );

        DefaultKafkaProducerFactory<String, OrderV2> factory =
                new DefaultKafkaProducerFactory<>(props);

        return new KafkaTemplate<>(factory);
    }
}
