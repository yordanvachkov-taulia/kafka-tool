package com.biranful.kafka.tool.producer.cli;

import com.beust.jcommander.JCommander;
public abstract class ArgumentsParser<T> {
    private final Class<T> type;

    public ArgumentsParser(Class<T> type) {
        this.type = type;
    }

    public T parse(String[] args) {
        try {
            T instance = type.newInstance();
            JCommander.newBuilder()
                    .addObject(instance)
                    .build()
                    .parse(args);

            return instance;
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println("Unable to instantiate argument resolver.");
        }

        return null;
    }
}
