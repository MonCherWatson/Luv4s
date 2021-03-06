package com.auguryrock.luv4s.domain.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Created by MonCherWatson on 08/05/2015.
 */
@JsonIgnoreProperties(value = {"coords"})
public class JsonObjectiveDescription {
    @JsonProperty(value = "score")
    private String score;
    @JsonProperty(value = "type")
    private String type;
    @JsonProperty(value = "name")
    private Map<String, String> names;

    public String getScore() {
        return score;
    }

    public String getType() {
        return type;
    }

    public Map<String, String> getNames() {
        return names;
    }
}
