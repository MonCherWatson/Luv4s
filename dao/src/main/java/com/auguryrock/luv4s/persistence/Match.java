package com.auguryrock.luv4s.persistence;

import javax.persistence.*;
import java.util.*;

@Entity
public class Match {
    @Id
    protected String id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "match")
    @MapKey(name = "colour")
    protected Map<Colour, World> worlds = new HashMap<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "match")
    @MapKey(name = "colour")
    protected Map<Colour, WvWMap> wvwMaps = new HashMap<>();

    protected Zone zone;

    public Match(String id) {
        this.id = id;
    }

    public Match(){
    }

    public String getId() {
        return id;
    }

    public Map<Colour, World> getWorlds() {
        return worlds;
    }

    public Map<Colour, WvWMap> getWvwMaps() {
        return wvwMaps;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public void addWorld(World world) {
        assert world.getColour() != null;
        worlds.put(world.getColour(), world);
        world.setMatch(this);
    }

    public void addMaps(WvWMap map) {
        assert map.getColour() != null;
        wvwMaps.put(map.getColour(), map);
        map.setMatch(this);
    }
}
