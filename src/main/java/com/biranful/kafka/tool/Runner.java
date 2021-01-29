package com.biranful.kafka.tool;

import com.biranful.kafka.tool.producer.cli.ProducerOptions;
import com.biranful.kafka.tool.producer.cli.ProducerOptionsParser;
import com.biranful.kafka.tool.producer.message.HeadersResolver;
import com.biranful.kafka.tool.producer.message.MessageResolver;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private final ProducerOptionsParser producerOptionsParser;
    private final MessageResolver messageResolver;
    private final HeadersResolver headersResolver;
    private final PropertiesFactory propertiesFactory;

    public Runner(ProducerOptionsParser producerOptionsParser,
                  MessageResolver messageResolver,
                  HeadersResolver headersResolver,
                  PropertiesFactory propertiesFactory) {
        this.producerOptionsParser = producerOptionsParser;
        this.messageResolver = messageResolver;
        this.headersResolver = headersResolver;
        this.propertiesFactory = propertiesFactory;
    }

    @Override
    public void run(String... args) throws Exception {
        ProducerOptions producerOptions = producerOptionsParser.parse(args);
        Producer<String, String> producer = new KafkaProducer<>(propertiesFactory.propertiesFromOptions(producerOptions));

        ProducerRecord<String, String> record = new ProducerRecord<>(producerOptions.getTopic(),
                producerOptions.getKey(),
                messageResolver.resolveValue(producerOptions));
        headersResolver.resolveHeaders(producerOptions)
                .forEach(header -> record.headers()
                        .add(header.getKey(), header.getKey().getBytes()));

        producer.send(record);
        producer.close();
    }
}
