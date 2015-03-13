package com.auguryrock.luv4s.persistence;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class WvWMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer pk;

    protected Type type;
    @OneToMany(cascade = CascadeType.ALL)
    protected Set<Structure> structures = new HashSet<>();

    public Set<Structure> getStructures() {
        return structures;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getPk() {
        return pk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WvWMap wvWMap = (WvWMap) o;

        if (pk != null ? !pk.equals(wvWMap.pk) : wvWMap.pk != null) return false;
        if (type != wvWMap.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pk != null ? pk.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    public enum Type {
        BlueHome, RedHome, GreenHome, Center;
    }


}
