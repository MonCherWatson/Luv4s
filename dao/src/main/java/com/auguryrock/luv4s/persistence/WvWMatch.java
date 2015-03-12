package com.auguryrock.luv4s.persistence;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class WvWMatch {
    @Id
    protected String id;
    @OneToMany(cascade = CascadeType.ALL)
    protected Set<World> worlds = new HashSet<>();
    @Transient
    protected List<WvWMap> wvwMaps = new ArrayList<WvWMap>();

    public WvWMatch(String id) {
        this.id = id;
    }

    public WvWMatch(){
    }

    public String getId() {
        return id;
    }

    public Set<World> getWorlds() {
        return worlds;
    }

    public List<WvWMap> getWvwMaps() {
        return wvwMaps;
    }
}
