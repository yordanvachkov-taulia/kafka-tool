package com.biranful.kafka.tool.producer.message;

import com.biranful.kafka.tool.producer.cli.CliOptions;
import com.biranful.kafka.tool.producer.cli.ProducerOptions;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Component
public class MessageResolver {
    private final ObjectMapper objectMapper;

    @Autowired
    public MessageResolver(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String resolveValue(CliOptions cliOptions) {
        ProducerOptions producerOptions = (ProducerOptions) cliOptions;
        try {
            Map values = objectMapper.readValue(new File(producerOptions.getJsonFilePath()), Map.class);
            return objectMapper.writeValueAsString(values);
        } catch (IOException e) {
            System.out.println("Unable to read JSON file with message value.");
        }

        return null;
    }
}
