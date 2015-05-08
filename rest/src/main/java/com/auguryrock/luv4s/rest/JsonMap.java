package com.auguryrock.luv4s.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties({"scores", "bonuses"})
public class JsonMap {
    @JsonProperty("type")
    protected Type type;
    @JsonProperty("objectives")
    protected List<JsonObjective> jsonObjectives;

    public Type getType() {
        return type;
    }

    public List<JsonObjective> getJsonObjectives() {
        return jsonObjectives;
    }

    public static enum Type {
        RedHome, GreenHome, BlueHome, Center
    }
}
