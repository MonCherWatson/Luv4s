package com.auguryrock.luv4s.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"owner_guild", "bonuses"})
public class JsonObjective {
    @JsonProperty("id")
    protected Integer id;
    @JsonProperty("owner")
    protected String owner;

    public Integer getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }
}
