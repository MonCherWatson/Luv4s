package com.auguryrock.luv4s.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;

/**
 * Created by Jose on 09/05/2015.
 */
@Entity
public class Period {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer pk;

    private LocalTime startTime;
    private LocalTime endTime;


}
