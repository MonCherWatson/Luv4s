package com.auguryrock.luv4s.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class JsonMatch {
    @JsonProperty("wvw_match_id")
    protected String id;
    @JsonProperty("red_world_id")
    protected Integer redWorldId;
    @JsonProperty("blue_world_id")
    protected Integer blueWorldId;
    @JsonProperty("green_world_id")
    protected Integer greenWorldId;
    @JsonProperty("start_time")
    protected Date startTime;
    @JsonProperty("end_time")
    protected Date endTime;

    public String getId() {
        return id;
    }

    public Integer getRedWorldId() {
        return redWorldId;
    }

    public Integer getBlueWorldId() {
        return blueWorldId;
    }

    public Integer getGreenWorldId() {
        return greenWorldId;
    }
}
