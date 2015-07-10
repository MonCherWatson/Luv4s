package com.auguryrock.luv4s.domain.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * Created by MonCherWatson on 22/05/2015.
 */
@Component
public class JsonWorldTranslationReader {
    private final ObjectMapper mapper = new ObjectMapper();

    public List<JsonWorldTranslation> getWorldTranslations() {
        final List<JsonWorldTranslation> list;
        try {
            list = mapper.readValue(getClass().getResourceAsStream("/worlds.json"), new TypeReference<List<JsonWorldTranslation>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return list;
    }
}
