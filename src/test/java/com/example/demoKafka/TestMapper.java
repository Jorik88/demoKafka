package com.example.demoKafka;

import com.example.demoKafka.model.ContentType;
import com.example.demoKafka.model.Message;
import com.example.demoKafka.model.MessageType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TestMapper {

    @Test
    public void test() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();


        Map<MessageType, String> typeStringMap = new HashMap<>();
        typeStringMap.put(MessageType.SLACK, "fiat_mon_info");
        Message message = new Message();
        message.setContent("Lorem ipsum ...");
        message.setContentType(ContentType.HTML);
        message.setSubject("subject");
        message.setMessageTypeDestinationMap(typeStringMap);
        message.setAttachments(Collections.emptyList());
        message.setInlineAttachments(Collections.emptyList());
        String value = mapper.writeValueAsString(message);
        System.out.println(value);
    }
}
