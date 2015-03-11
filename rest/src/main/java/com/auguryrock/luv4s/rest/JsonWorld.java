package com.auguryrock.luv4s.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by p127638 on 11.03.2015.
 */
public class JsonWorld {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;

    public JsonWorld() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
