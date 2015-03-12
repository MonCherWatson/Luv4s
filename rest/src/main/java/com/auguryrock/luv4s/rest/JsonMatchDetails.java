package com.auguryrock.luv4s.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by p127638 on 18.02.2015.
 */
@JsonIgnoreProperties({"scores"})
public class JsonMatchDetails {
    @JsonProperty("match_id")
    protected String matchId;
    @JsonProperty("maps")
    protected List<JsonMap> maps;

    public List<JsonMap> getMaps() {
        return maps;
    }
}
