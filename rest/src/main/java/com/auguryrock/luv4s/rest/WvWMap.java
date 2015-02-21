package com.auguryrock.luv4s.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by p127638 on 18.02.2015.
 */
@JsonIgnoreProperties({"scores", "bonuses"})
public class WvWMap {
    @JsonProperty("type")
    protected String type;
    @JsonProperty("objectives")
    protected List<Objective> objectives;
}
