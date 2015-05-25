package com.auguryrock.luv4s.domain.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by MonCherWatson on 22/05/2015.
 */
public class JsonWorldTranslation {
    @JsonProperty("world_id")
    public Integer world_id;
    @JsonProperty("name_de")
    public String name_de;
    @JsonProperty("name_fr")
    public String name_fr;
    @JsonProperty("name_es")
    public String name_es;
    @JsonProperty("name_en")
    public String name_en;
}
