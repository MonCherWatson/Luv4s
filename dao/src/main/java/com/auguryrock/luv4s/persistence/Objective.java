package com.auguryrock.luv4s.persistence;

import javax.persistence.*;

/**
 * Created by Jose on 08/05/2015.
 */
@Entity
public class Objective {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer pk;
    @ManyToOne
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
}
