package com.auguryrock.luv4s.persistence;

import javax.persistence.*;
import java.util.*;

@Entity
public class WvWMatch {
    @Id
    protected String id;
    @OneToMany(cascade = CascadeType.ALL)
    protected SortedSet<World> worlds = new TreeSet<>();
    @OneToMany(cascade = CascadeType.ALL)
    @MapKey(name = "colour")
    protected Map<Colour, WvWMap> wvwMaps = new HashMap<>();

    protected Zone zone;

    public WvWMatch(String id) {
        this.id = id;
    }

    public WvWMatch(){
    }

    public String getId() {
        return id;
    }

    public SortedSet<World> getWorlds() {
        return worlds;
    }

    public Map<Colour, WvWMap> getWvwMaps() {
        return wvwMaps;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }
}
