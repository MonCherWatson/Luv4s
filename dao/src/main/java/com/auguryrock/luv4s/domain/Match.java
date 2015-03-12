package com.auguryrock.luv4s.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by p127638 on 18.02.2015.
 */
public class Match {
    protected String id;
    protected List<World> worlds = new ArrayList<World>();
    protected List<WvWMap> wvwMaps = new ArrayList<WvWMap>();

    public Match() {

    }

    public Match(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public List<World> getWorlds() {
        return worlds;
    }

    public List<WvWMap> getWvwMaps() {
        return wvwMaps;
    }
}