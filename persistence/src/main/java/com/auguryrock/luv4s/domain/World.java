package com.auguryrock.luv4s.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class World implements Comparable<World> {
    public static final String WORLD = "world";
    @Id
    protected Integer id;
    @ManyToOne
    @JoinColumn(name = "match_id")
    @JsonIgnore
    protected Matchup matchup;
    protected Colour colour;
    protected Integer score;
    protected String nameKey;


    public World(Integer id, Colour colour) {
        assert id != null;
        this.id = id;
        this.colour = colour;
        this.nameKey = WORLD + id;
    }

    public World() {
    }

    public Integer getId() {
        return id;
    }

    public Colour getColour() {
        return colour;
    }

    public String getNameKey() {
        return nameKey;
    }

    public Matchup getMatchup() {
        return matchup;
    }

    public void setMatchup(Matchup matchup) {
        this.matchup = matchup;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        World world = (World) o;

        if (colour != world.colour) return false;
        if (id != null ? !id.equals(world.id) : world.id != null) return false;
        if (nameKey != null ? !nameKey.equals(world.nameKey) : world.nameKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (colour != null ? colour.hashCode() : 0);
        result = 31 * result + (nameKey != null ? nameKey.hashCode() : 0);
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
