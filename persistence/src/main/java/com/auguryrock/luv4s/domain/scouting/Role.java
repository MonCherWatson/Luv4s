package com.auguryrock.luv4s.domain.scouting;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by MonCherWatson on 19/06/2015.
 */
//@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer pk;
    private User user;
    private ScoutingKey scoutingKey;
    private RoleType roleType;
}
