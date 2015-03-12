package com.auguryrock.luv4s.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Created by p127638 on 12.03.2015.
 */
@Component
public class JsonObjectiveDescriptionReader {
    private final ObjectMapper mapper = new ObjectMapper();

    public Map getObjectiveDescriptions() throws IOException {
        final Map map = mapper.readValue(getClass().getResourceAsStream("/objectives.json"), Map.class);
        return map;
    }
}
