package com.auguryrock.luv4s.domain.scouting;

import com.auguryrock.luv4s.domain.Objective;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ScoutingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer pk;
    private Date start;
    private Date end;
    private String description;
    @ManyToOne
    private Player player;
    @ManyToOne
    private ScoutingKey scoutingKey;
    @ManyToOne
    private Objective objective;

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
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
