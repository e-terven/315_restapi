package com.katia.spring.security.configs;

import org.springframework.boot.logging.LogLevel;
import org.springframework.core.convert.converter.Converter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class LoggingLevelConverter implements Converter<String, Map<String, LogLevel>> {

    @Override
    public Map<String, LogLevel> convert(String source) {
        Map<String, LogLevel> loggingLevelMap = new HashMap<>();
        Arrays.stream(source.split(","))
                .map(s -> s.split(":"))
                .forEach(arr -> loggingLevelMap.put(arr[0].trim(), LogLevel.valueOf(arr[1].trim().toUpperCase())));
        return loggingLevelMap;
    }
}
