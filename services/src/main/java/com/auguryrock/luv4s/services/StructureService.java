package com.auguryrock.luv4s.services;

import com.auguryrock.luv4s.domain.Match;
import com.auguryrock.luv4s.domain.World;
import com.auguryrock.luv4s.rest.Gw2V1Client;
import com.auguryrock.luv4s.rest.JsonObjective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by p127638 on 18.02.2015.
 */
@Component
public class StructureService {
    @Autowired
    Gw2V1Client gw2V1Client;
    public Match getAvailableStructures(World world) {
        for (JsonObjective jsonObjective : gw2V1Client.getObjectiveName()ObjectiveName()) {

        }
        return null;
    }
}
