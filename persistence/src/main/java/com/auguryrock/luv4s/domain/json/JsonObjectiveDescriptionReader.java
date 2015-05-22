package com.auguryrock.luv4s.domain.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class JsonObjectiveDescriptionReader {
    private final ObjectMapper mapper = new ObjectMapper();

    public Map<String, JsonObjectiveDescription> getObjectiveDescriptions() {
        final Map map;
        try {
            map = mapper.readValue(getClass().getResourceAsStream("/objectives.json"), new TypeReference<Map<String, JsonObjectiveDescription>>() { });
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return map;
    }
}
