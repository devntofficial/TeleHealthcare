package com.bmc.doctorservice.configurations;

import com.bmc.ratingservice.entities.RatingEntity;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfiguration {
    @Value("${kafka.bootstrap.servers}")
    private String bootstrapServers;

    @Value("${kafka.group.id}")
    private String groupId;

    private Map<String,Object> getConfig() {
        Map<String,Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG,groupId);
        return  configProps;
    }

    @Bean
    ConsumerFactory<String, RatingEntity> ratingConsumerFactory(){
        JsonDeserializer deserializer = new JsonDeserializer();
        deserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory(getConfig(),new StringDeserializer(),deserializer);
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory ratingListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(ratingConsumerFactory());
        return  factory;
    }

}
