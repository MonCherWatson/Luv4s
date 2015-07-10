package com.auguryrock.luv4s.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by MonCherWatson on 08/05/2015.
 */
@Entity
public class Objective implements Comparable<Objective> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer pk;
    @ManyToOne
    @JsonIgnore
    protected WvWMap map;
    @ManyToOne
    protected ObjectiveDescription description;
    @ManyToOne
    protected World owner;

    public WvWMap getMap() {
        return map;
    }

    public void setMap(WvWMap map) {
        this.map = map;
    }

    public ObjectiveDescription getDescription() {
        return description;
    }

    public void setDescription(ObjectiveDescription description) {
        this.description = description;
    }

    public World getOwner() {
        return owner;
    }

    public void setOwner(World owner) {
        this.owner = owner;
    }

    @Override
    public int compareTo(Objective o) {
        return pk.compareTo(o.pk);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Objective objective = (Objective) o;

        return pk.equals(objective.pk);

    }

    @Override
    public int hashCode() {
        return pk.hashCode();
    }
}
