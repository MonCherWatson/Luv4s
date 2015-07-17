package com.auguryrock.luv4s.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

/**
 * Created by MonCherWatson on 17/07/2015.
 */
@Component("customJacksonJsonProvider")
public class CustomJacksonJsonProvider extends JacksonJsonProvider {

    @PostConstruct
    public void init() {
        ObjectMapper objectMapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());

        objectMapper.registerModule(module);

        setMapper(objectMapper);
    }
}
