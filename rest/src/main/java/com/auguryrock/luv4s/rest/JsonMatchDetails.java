package com.auguryrock.luv4s.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties({"scores"})
public class JsonMatchDetails {
    @JsonProperty("match_id")
    protected String matchId;
    @JsonProperty("maps")
    protected List<JsonMap> maps;
    @JsonProperty("scores")
    protected List<Integer> scores;

    public List<JsonMap> getMaps() {
        return maps;
    }

    public List<Integer> getScores() {
        return scores;
    }
}
