package com.sharipov.notification_service.config;

import com.sharipov.notification_service.event.NotificationEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {


    @Autowired
    Environment environment;


    @Bean
    ConsumerFactory<String, NotificationEvent> consumerFactory() {
        JsonDeserializer<NotificationEvent> deserializer = new JsonDeserializer<>(NotificationEvent.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");  // Принимаем все пакеты (или укажите нужный)
        deserializer.setUseTypeMapperForKey(true);


        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getProperty(""));
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, environment.getProperty(""));
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
    }

//
//@Bean
//    ConcurrentKafkaListenerContainerFactory<String, >
}
