package com.example.demoKafka;

import com.example.demoKafka.model.Attachment;
import com.example.demoKafka.model.ContentType;
import com.example.demoKafka.model.Message;
import com.example.demoKafka.model.MessageType;
import com.example.demoKafka.service.SenderService;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoKafkaApplicationTests {

	private String filePath = "/home/jorik/MyProjects/demoKafka/src/test/resources/templates/test.png";

	@Autowired
	private SenderService senderService;

	@Test
	public void contextLoads() throws InterruptedException, IOException {
		byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));
		String encodedString = Base64.getEncoder().encodeToString(fileContent);
		Attachment attachment = new Attachment("test.png", encodedString);
		List<Attachment> attachmentList = new ArrayList<>();
		attachmentList.add(attachment);

		Map<MessageType, String> typeStringMap = new HashMap<>();
		typeStringMap.put(MessageType.EMAIL, "*380970704271");
		Message message = new Message();
		message.setContent("Testing message-sender service");
		message.setContentType(ContentType.HTML);
		message.setSubject("Information");
		message.setMessageTypeDestinationMap(typeStringMap);
		message.setAttachments(Collections.emptyList());
		message.setInlineAttachments(Collections.emptyList());
		senderService.sendMessage(message);
		Thread.sleep(20000);
	}

}
