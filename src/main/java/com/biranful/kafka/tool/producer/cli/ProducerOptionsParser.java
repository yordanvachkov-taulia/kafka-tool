package com.biranful.kafka.tool.producer.cli;

import org.springframework.stereotype.Component;

@Component
public class ProducerOptionsParser extends ArgumentsParser<ProducerOptions> {
    public ProducerOptionsParser() {
        super(ProducerOptions.class);
    }
}
