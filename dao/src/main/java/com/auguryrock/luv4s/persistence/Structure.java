package com.auguryrock.luv4s.persistence;

import javax.persistence.*;
import java.util.List;

@Entity
public class Structure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer pk;
    protected Integer id;
    protected Type type;
    @Transient
    protected WvWMap wvwMap;
    @Transient
    protected World owner;
//    protected List<ScoutingSession> scoutingSessions;


    public Structure() {
    }

    public Structure(Integer id, Type type, WvWMap wvwMap) {
        this.id = id;
        this.type = type;
        this.wvwMap = wvwMap;
    }

    public World getOwner() {
        return owner;
    }

    public void setOwner(World owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Structure structure = (Structure) o;

        if (id != null ? !id.equals(structure.id) : structure.id != null) return false;
        if (pk != null ? !pk.equals(structure.pk) : structure.pk != null) return false;
        if (type != structure.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pk != null ? pk.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    public enum Type {
        keep, castle, tower
    }
}
