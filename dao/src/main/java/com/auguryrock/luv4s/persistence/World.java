package com.auguryrock.luv4s.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class World {
    @Id
    protected Integer id;
    @ManyToOne
    protected WvWMatch match;
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

    public WvWMatch getMatch() {
        return match;
    }

    public void setMatch(WvWMatch match) {
        this.match = match;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        World world = (World) o;

        if (colour != world.colour) return false;
        if (id != null ? !id.equals(world.id) : world.id != null) return false;
        if (name != null ? !name.equals(world.name) : world.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (colour != null ? colour.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
