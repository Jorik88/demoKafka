package com.example.demoKafka.service;

import com.example.demoKafka.producer.MessageProducer;
import com.example.demoKafka.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SenderService {

    private final MessageProducer producer;

    @Autowired
    public SenderService(MessageProducer producer) {
        this.producer = producer;
    }

    public void sendMessage(Message message) {
        producer.pushPaymentResponse(message);
    }
}
