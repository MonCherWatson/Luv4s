package com.auguryrock.luv4s.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Jose on 08/05/2015.
 */
@Entity
public class ObjectiveDescription {
    @Id
    private Integer id;
    private ObjectiveType type;
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
