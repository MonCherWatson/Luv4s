package com.auguryrock.luv4s.domain.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Created by MonCherWatson on 22/05/2015.
 */
@Component
public class JsonWorldTranslationReader {
    private final ObjectMapper mapper = new ObjectMapper();

    public Map<String, JsonWorldTranslation> getObjectiveDescriptions() {
        final Map map;
        try {
            map = mapper.readValue(getClass().getResourceAsStream("/worlds.json"), new TypeReference<Map<String, JsonWorldTranslation>>() { });
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return map;
    }
}
