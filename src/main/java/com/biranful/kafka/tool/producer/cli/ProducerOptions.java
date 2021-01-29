package com.biranful.kafka.tool.producer.cli;

import com.beust.jcommander.Parameter;

public class ProducerOptions implements CliOptions {

    @Parameter(names = {"-b", "--brokers"})
    private String brokers;

    @Parameter(names = {"-t", "--topic"})
    private String topic;

    @Parameter(names = {"-k", "--key"})
    private String key;

    @Parameter(names = {"-f", "--file"}, description = "Path to the file containing the message value in JSON.")
    private String jsonFilePath;

    @Parameter(names = {"-h", "--headers"}, description = "Path to file containing headers in a key=value pair format.")
    private String headersFilePath;

    @Override
    public String getBrokers() {
        return brokers;
    }

    public void setBrokers(String brokers) {
        this.brokers = brokers;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getJsonFilePath() {
        return jsonFilePath;
    }

    public void setJsonFilePath(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }

    public String getHeadersFilePath() {
        return headersFilePath;
    }

    public void setHeadersFilePath(String headersFilePath) {
        this.headersFilePath = headersFilePath;
    }
}
