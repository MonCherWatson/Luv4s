package com.auguryrock.luv4s.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by MonCherWatson on 08/05/2015.
 */
@Entity
public class ObjectiveDescription {
    public static final String OBJECTIVE_DESCRIPTION = "objectiveDescription";

    @Id
    private Integer id;
    private ObjectiveType type;
    private String nameKey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ObjectiveType getType() {
        return type;
    }

    public void setType(ObjectiveType type) {
        this.type = type;
    }

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }
}
