package com.auguryrock.luv4s.domain.scouting;

import com.auguryrock.luv4s.domain.World;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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
}
