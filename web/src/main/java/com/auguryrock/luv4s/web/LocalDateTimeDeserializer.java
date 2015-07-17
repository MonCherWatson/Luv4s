package com.auguryrock.luv4s.web;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 * Created by MonCherWatson on 17/07/2015.
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        long ms = p.getBigIntegerValue().longValue();
        LocalDateTime localDateTime = LocalDateTime.from(Instant.ofEpochMilli(ms));
        System.out.println(localDateTime);

        return localDateTime;
    }
}
