package com.auguryrock.luv4s.domain.scouting;

import com.auguryrock.luv4s.domain.Objective;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class ScoutingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    private Long startTimestamp;
    private Long endTimestamp;
    private String description;
    @ManyToOne
    private Player player;
    @ManyToOne
    @JsonIgnore
    private ScoutingKey scoutingKey;
    @ManyToOne
    @JsonIgnore
    private Objective objective;

    public ScoutingSession() {
    }

    public ScoutingSession(Long start, Long endTimestamp, String description) {
        this.startTimestamp = start;
        this.endTimestamp = endTimestamp;
        this.description = description;
    }

    public Long getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Long getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(Long endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ScoutingKey getScoutingKey() {
        return scoutingKey;
    }

    public void setScoutingKey(ScoutingKey scoutingKey) {
        this.scoutingKey = scoutingKey;
    }

    public Objective getObjective() {
        return objective;
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
    }
}
