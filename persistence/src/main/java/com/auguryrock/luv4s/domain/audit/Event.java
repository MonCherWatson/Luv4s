package com.auguryrock.luv4s.domain.audit;

import com.auguryrock.luv4s.domain.scouting.ScoutingKey;
import com.auguryrock.luv4s.domain.scouting.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by MonCherWatson on 19/06/2015.
 */
//@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer pk;
    private User user;
    private String ipAddress;
    private ScoutingKey scoutingKey;
    private EventType eventType;
}
