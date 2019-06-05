package com.example.demoKafka;

import com.example.demoKafka.model.ContentType;
import com.example.demoKafka.model.Message;
import com.example.demoKafka.model.MessageType;
import com.example.demoKafka.service.SenderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoKafkaApplicationTests {

	@Autowired
	private SenderService senderService;

	@Test
	public void contextLoads() {

		Map<MessageType, String> typeStringMap = new HashMap<>();
		typeStringMap.put(MessageType.SLACK, "fiat_mon_info");
		Message message = new Message();
		message.setContent("Lorem ipsum ...");
		message.setContentType(ContentType.HTML);
		message.setSubject("subject");
		message.setMessageTypeDestinationMap(typeStringMap);
		message.setAttachments(Collections.emptyList());
		message.setInlineAttachments(Collections.emptyList());
		senderService.sendMessage(message);
	}

}
