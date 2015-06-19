package com.auguryrock.luv4s.domain.scouting;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by MonCherWatson on 19/06/2015.
 */
@Entity
public class ScoutingKey {
    @Id
    private String token;

    public ScoutingKey() {
    }

    public ScoutingKey(String token) {
        this.token = token;
    }
}
