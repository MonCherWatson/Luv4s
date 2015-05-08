package com.auguryrock.luv4s.persistence;

import javax.persistence.*;

@Entity
public class World implements Comparable<World>{
    @Id
    protected Integer id;
    @ManyToOne
    @JoinColumn(name ="match_id")
    protected WvWMatch match;
    protected Colour colour;
    protected Integer score;
    @Transient
    protected String name;

    public World(Integer id, Colour colour) {
        this.id = id;
        this.colour = colour;
    }

    public World() {
    }

    public Integer getId() {
        return id;
    }

    public Colour getColour() {
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

    public void setName(String name) {
        this.name = name;
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


    @Override
    public int compareTo(World world) {
        assert world.getId() != null;
        assert id != null;
        return id.compareTo(world.getId());
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }



}
