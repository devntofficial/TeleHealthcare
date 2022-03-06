package com.bmc.doctorservice.configurations;

import com.bmc.doctorservice.entities.DoctorEntity;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfiguration {
    @Value("${kafka.bootstrap.servers}")
    private String bootstrapServers;

    @Bean
    ProducerFactory<String, DoctorEntity> producerFactory(){
        return new DefaultKafkaProducerFactory<>(getConfig());
    }

    private Map<String,Object> getConfig() {
        Map<String,Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return  configProps;
    }

    @Bean
    public KafkaTemplate<String, DoctorEntity> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }
}
