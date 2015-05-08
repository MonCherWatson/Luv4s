package com.auguryrock.luv4s.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Created by Jose on 08/05/2015.
 */
@JsonIgnoreProperties(value = {"coords"})
public class JsonObjectiveDescription {
    @JsonProperty(value = "score")
    private String score;
    @JsonProperty(value = "type")
    private String type;
    @JsonProperty(value = "name")
    private Map<String, String> names;

}
