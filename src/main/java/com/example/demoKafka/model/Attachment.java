package com.example.demoKafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Attachment {
    private String fileName;
    private String base64Data;
}
