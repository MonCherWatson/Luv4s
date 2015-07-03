package com.auguryrock.luv4s.domain.scouting;

import com.auguryrock.luv4s.domain.World;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by MonCherWatson on 19/06/2015.
 */
@Entity
public class ScoutingKey {
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Id
    private String uuid;
    @ManyToOne
    private World world;
    @OneToMany
    private Set<ScoutingSession> scoutingSessions;

    public ScoutingKey() {
    }

    public ScoutingKey(World world) {
        this.world = world;
    }

    public ScoutingKey(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Set<ScoutingSession> getScoutingSessions() {
        return scoutingSessions;
    }

    public void setScoutingSessions(Set<ScoutingSession> scoutingSessions) {
        this.scoutingSessions = scoutingSessions;
    }
}
