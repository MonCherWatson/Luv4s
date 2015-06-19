package com.auguryrock.luv4s.domain.scouting;

import javax.persistence.*;

/**
 * Created by MonCherWatson on 19/06/2015.
 */
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer pk;
    @ManyToOne
    private Player player;
    @OneToOne
    private ScoutingKey scoutingKey;
    private RoleType roleType;

    public Integer getPk() {
        return pk;
    }

    public Player getPlayer() {
        return player;
    }

    public ScoutingKey getScoutingKey() {
        return scoutingKey;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setScoutingKey(ScoutingKey scoutingKey) {
        this.scoutingKey = scoutingKey;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}
