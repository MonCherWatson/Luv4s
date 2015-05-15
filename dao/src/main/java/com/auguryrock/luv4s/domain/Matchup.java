package com.auguryrock.luv4s.domain;

import javax.persistence.*;
import java.util.*;

@Entity
public class Matchup {
    @Id
    protected String id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matchup", fetch = FetchType.EAGER)
    @MapKey(name = "colour")
    protected Map<Colour, World> worlds = new HashMap<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matchup", fetch = FetchType.EAGER)
    @MapKey(name = "colour")
    protected Map<Colour, WvWMap> wvwMaps = new HashMap<>();

    protected Zone zone;

    public Matchup(String id) {
        this.id = id;
    }

    public Matchup(){
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
        world.setMatchup(this);
        worlds.put(world.getColour(), world);
    }

    public void addMaps(WvWMap map) {
        assert map.getColour() != null;
        wvwMaps.put(map.getColour(), map);
        map.setMatchup(this);
    }
}
