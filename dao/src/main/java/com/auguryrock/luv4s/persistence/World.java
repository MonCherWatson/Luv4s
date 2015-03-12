package com.auguryrock.luv4s.persistence;


@Entity
public class World {
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
}
