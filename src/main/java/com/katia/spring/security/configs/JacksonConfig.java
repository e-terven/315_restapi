package com.katia.spring.security.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        // Включить параметр SerializationFeature.FAIL_ON_EMPTY_BEANS
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, true);

        // Отключить создание прокси классов Hibernate при загрузке сущностей
        Hibernate5Module module = new Hibernate5Module();
        module.disable(Hibernate5Module.Feature.FORCE_LAZY_LOADING);
        objectMapper.registerModule(module);

        return objectMapper;
    }
}
