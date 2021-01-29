package com.biranful.kafka.tool;

import com.biranful.kafka.tool.producer.cli.CliOptions;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PropertiesFactory {
    Map<String, Object> propertiesFromOptions(CliOptions cliOptions) {
        Map<String, Object> properties = new HashMap<>();

        properties.put("bootstrap.servers", cliOptions.getBrokers());
        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());

        return properties;
    }
}
