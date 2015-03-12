package com.auguryrock.luv4s.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by p127638 on 18.02.2015.
 */
@JsonIgnoreProperties({"owner_guild", "bonuses"})
public class JsonObjective {
    @JsonProperty("id")
    protected String id;
    @JsonProperty("owner")
    protected Owner owner;

    public String getId() {
        return id;
    }

    public Owner getOwner() {
        return owner;
    }

    public enum Owner {
        Red, Green, Blue
    }
}
