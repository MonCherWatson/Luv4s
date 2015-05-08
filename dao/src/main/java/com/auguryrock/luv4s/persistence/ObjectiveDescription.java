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

}
