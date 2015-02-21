package com.auguryrock.luv4s.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by p127638 on 18.02.2015.
 */
public class WvWMatch {
    @JsonProperty("wvw_match_id")
    protected String id;
    @JsonProperty("red_world_id")
    protected String redWorldId;
    @JsonProperty("blue_world_id")
    protected String blueWorldId;
    @JsonProperty("green_world_id")
    protected String greenWorldId;
    @JsonProperty("start_time")
    protected Date startTime;
    @JsonProperty("end_time")
    protected Date endTime;

    public String getId() {
        return id;
    }

    public String getRedWorldId() {
        return redWorldId;
    }

    public String getBlueWorldId() {
        return blueWorldId;
    }

    public String getGreenWorldId() {
        return greenWorldId;
    }
}
