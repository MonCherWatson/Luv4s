package com.auguryrock.luv4s.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by p127638 on 17.02.2015.
 */
public class WvWMap {
    protected Type type;
    protected List<Structure> structures = new ArrayList<Structure>();

    public List<Structure> getStructures() {
        return structures;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        BlueHome, RedHome, GreenHome, Center;
    }
}
