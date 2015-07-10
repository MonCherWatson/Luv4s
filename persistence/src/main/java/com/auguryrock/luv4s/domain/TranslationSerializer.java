package com.auguryrock.luv4s.domain;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by MonCherWatson on 29/05/2015.
 */
public class TranslationSerializer extends JsonSerializer<Translation> {

    @Override
    public void serialize(Translation translation, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartObject();
        gen.writeStringField(translation.getNameKey(), translation.getValue());
        gen.writeEndObject();
    }
}
