package com.auguryrock.luv4s.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by Jose on 01/05/2015.
 */
public abstract class AbstractMockRestClient {

    protected <T> T unmarshall(String fileName, Class<T> type) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(this.getClass().getResourceAsStream(fileName), type);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    protected <T> T unmarshallWithTypeReference(String fileName, TypeReference<T> typeRef) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(this.getClass().getResourceAsStream(fileName), typeRef);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
