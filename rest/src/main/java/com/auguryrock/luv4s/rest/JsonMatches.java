package com.auguryrock.luv4s.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class JsonMatches {
    @JsonProperty("wvw_matches")
    protected List<JsonMatch> jsonMatches;

    public List<JsonMatch> getJsonMatches() {
        return jsonMatches;
    }
}
