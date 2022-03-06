package com.bmc.notificationservice.configurations;

import com.bmc.appointmentservice.entities.AppointmentEntity;
import com.bmc.appointmentservice.entities.PrescriptionEntity;
import com.bmc.doctorservice.entities.DoctorEntity;
import com.bmc.userservice.entities.UserEntity;
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
public class KafkaConsumerConfig {
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
    ConsumerFactory<String, DoctorEntity> doctorConsumerFactory(){
        JsonDeserializer deserializer = new JsonDeserializer();
        deserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory(getConfig(),new StringDeserializer(),deserializer);
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory doctorListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(doctorConsumerFactory());
        return  factory;
    }

    @Bean
    ConsumerFactory<String, UserEntity> userConsumerFactory(){
        JsonDeserializer deserializer = new JsonDeserializer();
        deserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory(getConfig(),new StringDeserializer(),deserializer);
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory userListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(userConsumerFactory());
        return  factory;
    }

    @Bean
    ConsumerFactory<String, AppointmentEntity> appointmentConsumerFactory(){
        JsonDeserializer deserializer = new JsonDeserializer();
        deserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory(getConfig(),new StringDeserializer(),deserializer);
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory appointmentListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(appointmentConsumerFactory());
        return  factory;
    }

    @Bean
    ConsumerFactory<String, PrescriptionEntity> prescriptionConsumerFactory(){
        JsonDeserializer deserializer = new JsonDeserializer();
        deserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory(getConfig(),new StringDeserializer(),deserializer);
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory prescriptionListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(prescriptionConsumerFactory());
        return  factory;
    }
}
