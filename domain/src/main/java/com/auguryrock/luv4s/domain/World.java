package com.auguryrock.luv4s.domain;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by p127638 on 17.02.2015.
 */
public class World {
    protected String id;
    protected Colours colour;
    protected String name;
    protected List<Map> maps = new ArrayList<Map>();

    public World(String id, Colours colour, String name) {
        this.id = id;
        this.colour = colour;
        this.name = name;
    }

    public World() {
    }
}
