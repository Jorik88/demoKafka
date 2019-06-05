package com.example.demoKafka.producer;

import com.example.demoKafka.config.KafkaConfiguration;
import com.example.demoKafka.model.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class MessageProducer {
    private KafkaConfiguration kafkaConfiguration;
    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper mapper;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Autowired
    public MessageProducer(KafkaConfiguration kafkaConfiguration, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper mapper) {
        this.kafkaConfiguration = kafkaConfiguration;
        this.kafkaTemplate = kafkaTemplate;
        this.mapper = mapper;
    }

    public void pushPaymentResponse(Message message) {
        executorService.submit(new PushTransferResponseTask(message));
    }

    private class PushTransferResponseTask implements Runnable {
        private Message message;

        PushTransferResponseTask(Message message) {
            super();
            this.message = message;
        }

        @Override
        public void run() {
            try {
                log.info("Send a message record to kafka topic, message={}", message);
                sendRecord();
            } catch (Exception e) {
                log.warn("Can't send message record to kafka topic message={}", message, e);
            }
        }

        private void sendRecord() {
            try {
                ProducerRecord<String, String> record = new ProducerRecord<>(kafkaConfiguration.getTopicName(), mapper.writeValueAsString(message));
                kafkaTemplate.send(record).addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                    @Override
                    public void onSuccess(SendResult<String, String> result) {

                    }

                    @Override
                    public void onFailure(Throwable ex) {

                    }
                });
            } catch (JsonProcessingException e) {
                log.warn("Error sent record to kafka topic, message={}", message, e);

            }
        }
    }
}
