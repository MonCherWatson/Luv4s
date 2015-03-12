package com.auguryrock.luv4s.persistence;

import java.util.ArrayList;
import java.util.List;

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
