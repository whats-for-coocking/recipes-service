package com.whatsforcoocking.recipeservice.config.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopic {

    @Value("${spring.kafka.topics.recipes-topic}")
    private String recipeTopic;

//    @Bean
//    public NewTopic recipesTopic(){
//        return TopicBuilder.name(recipeTopic).build();
//    }
}
