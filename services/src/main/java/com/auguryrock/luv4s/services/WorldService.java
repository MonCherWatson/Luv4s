package com.auguryrock.luv4s.services;

import com.auguryrock.luv4s.persistence.World;
import com.auguryrock.luv4s.persistence.WorldRepository;
import com.auguryrock.luv4s.rest.Gw2V2Client;
import com.auguryrock.luv4s.rest.JsonWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

//@Component
public class WorldService {
    @Autowired
    protected WorldRepository worldRepository;
    @Autowired
    protected Gw2V2Client gw2V2Client;

    @Transactional
    public void persistWorld() {
        final List<JsonWorld> worlds = gw2V2Client.getWorlds(Gw2V2Client.ALL_WORDS, "fr");
        for (JsonWorld jsonWorld: worlds) {
//            World world = new World(jsonWorld.getId(),)
        }
    }

}
