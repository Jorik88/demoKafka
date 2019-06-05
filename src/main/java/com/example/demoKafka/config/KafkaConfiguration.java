package com.example.demoKafka.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app.configuration.kafka")
public class KafkaConfiguration {

    private String groupId;
    private String bootstrapServers;
    private int startUpReconnectDelayInSec;
    private String producerClientId;
    private String topicName;

}
