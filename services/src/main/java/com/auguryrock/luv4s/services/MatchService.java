package com.auguryrock.luv4s.services;

import com.auguryrock.luv4s.persistence.*;
import com.auguryrock.luv4s.rest.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MatchService {
    @Autowired
    private Gw2V1Client gw2V1Client;
    @Autowired
    private Gw2V2Client gw2V2Client;
    @Autowired
    private WvWMatchRepository matchRepository;

    public List<WvWMatch> createMatches() {
        final ArrayList<WvWMatch> matches = new ArrayList<>();
        for (JsonMatch gw2Match : gw2V1Client.getAllMatches().getJsonMatches()) {
            WvWMatch match = new WvWMatch(gw2Match.getId());
            match.getWorlds().add(new World(gw2Match.getBlueWorldId(), Colours.Blue));
            match.getWorlds().add(new World(gw2Match.getGreenWorldId(), Colours.Green));
            match.getWorlds().add(new World(gw2Match.getRedWorldId(), Colours.Red));
            match = matchRepository.save(match);
            matches.add(match);
        }
        return matches;
    }


    protected Map<Integer, String> getWorldNames(String lang) {
        final HashMap<Integer, String> map = new HashMap<>();
        final List<JsonWorld> allJsonWorlds = gw2V2Client.getWorlds("all", lang);
        for (JsonWorld jsonWorld : allJsonWorlds) {
            map.put(jsonWorld.getId(), jsonWorld.getName());
        }
        return map;
    }


}
