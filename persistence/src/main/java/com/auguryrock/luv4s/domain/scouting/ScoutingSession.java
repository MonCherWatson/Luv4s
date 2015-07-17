package com.auguryrock.luv4s.domain.scouting;

import com.auguryrock.luv4s.domain.Objective;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
public class ScoutingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
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

    public ScoutingSession(LocalDateTime start, LocalDateTime endTime, String description) {
        this.startTime = start;
        this.endTime = endTime;
        this.description = description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
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
