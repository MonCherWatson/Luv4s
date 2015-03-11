package com.auguryrock.luv4s.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by p127638 on 18.02.2015.
 */
public class JsonMatches {
    @JsonProperty("wvw_matches")
    protected List<JsonMatch> jsonMatches;

    public List<JsonMatch> getJsonMatches() {
        return jsonMatches;
    }
}
