package com.example.demoKafka;

import com.example.demoKafka.model.ContentType;
import com.example.demoKafka.model.Message;
import com.example.demoKafka.model.MessageType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TestMapper {

    private String filePath = "/home/jorik/MyProjects/demoKafka/src/test/resources/templates/test.png";

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

    @Test

    public void testLoadFile() throws IOException {

        byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        System.out.println(encodedString);
    }
}
