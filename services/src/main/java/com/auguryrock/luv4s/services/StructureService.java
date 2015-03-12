package com.auguryrock.luv4s.services;

import com.auguryrock.luv4s.persistence.Match;
import com.auguryrock.luv4s.persistence.World;
import com.auguryrock.luv4s.rest.Gw2V1Client;
import com.auguryrock.luv4s.rest.JsonObjective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StructureService {
    @Autowired
    Gw2V1Client gw2V1Client;
    public Match getAvailableStructures(World world) {
        return null;
    }
}
