package com.auguryrock.luv4s.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Jose on 22/05/2015.
 */
@Component
public class JsonWorldNameReader {
    private final ObjectMapper mapper = new ObjectMapper();

    public Map<String, JsonWorld> getObjectiveDescriptions() {
        final Map map;
        try {
            map = mapper.readValue(getClass().getResourceAsStream("/worlds.json"), new TypeReference<Map<String, JsonWorld>>() { });
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return map;
    }
}
