package com.example.demoKafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InlineAttachment {
    private String contentId;
    private String base64Data;
    private String contentType;
}
