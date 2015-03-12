package com.auguryrock.luv4s.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class World {
    @Id
    protected Integer id;
    protected Colours colour;
    protected String name;

    public World(Integer id, Colours colour, String name) {
        this.id = id;
        this.colour = colour;
        this.name = name;
    }

    public World() {
    }

    public Integer getId() {
        return id;
    }

    public Colours getColour() {
        return colour;
    }

    public String getName() {
        return name;
    }
}
