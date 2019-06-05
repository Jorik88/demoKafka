package com.example.demoKafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private Map<MessageType, String> messageTypeDestinationMap;
    private String subject;
    private String content;
    private ContentType contentType;
    private List<Attachment> attachments;
    private List<InlineAttachment> inlineAttachments;

}
