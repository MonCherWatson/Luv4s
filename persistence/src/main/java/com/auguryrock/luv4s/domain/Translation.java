package com.auguryrock.luv4s.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Jose on 22/05/2015.
 */
@Entity
public class Translation {
    @Id
    private Integer id;
    private String value;
    private Language language;
}
