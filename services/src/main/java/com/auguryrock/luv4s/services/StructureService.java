package com.auguryrock.luv4s.services;

import com.auguryrock.luv4s.persistence.WvWMatch;
import com.auguryrock.luv4s.persistence.World;
import com.auguryrock.luv4s.rest.Gw2V1Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class StructureService {
    @Autowired
    Gw2V1Client gw2V1Client;
    public WvWMatch getAvailableStructures(World world) {
        return null;
    }
}
