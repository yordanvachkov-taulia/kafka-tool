package com.biranful.kafka.tool.producer.message;

import com.biranful.kafka.tool.producer.cli.CliOptions;
import com.biranful.kafka.tool.producer.cli.ProducerOptions;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class HeadersResolver {
    private final ObjectMapper objectMapper;

    @Autowired
    public HeadersResolver(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Header> resolveHeaders(CliOptions cliOptions) {
        ProducerOptions producerOptions = (ProducerOptions) cliOptions;

        List<Header> headers = new ArrayList<>();
        try {
            Map<String, String> values = objectMapper.readValue(new File(producerOptions.getHeadersFilePath()), Map.class);
            for (Map.Entry<String, String> entry : values.entrySet()) {
                headers.add(new Header(entry.getKey(), entry.getValue()));
            }

            return headers;
        } catch (IOException e) {
            System.out.println("Unable to read JSON file with message headers.");
        }

        return null;
    }
}
