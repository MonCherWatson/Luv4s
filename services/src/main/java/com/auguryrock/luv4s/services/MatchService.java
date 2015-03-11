package com.auguryrock.luv4s.services;

import com.auguryrock.luv4s.domain.Colours;
import com.auguryrock.luv4s.domain.Match;
import com.auguryrock.luv4s.domain.World;
import com.auguryrock.luv4s.rest.Gw2V1Client;
import com.auguryrock.luv4s.rest.Gw2V2Client;
import com.auguryrock.luv4s.rest.JsonWorld;
import com.auguryrock.luv4s.rest.JsonMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by p127638 on 18.02.2015.
 */
@Component
public class MatchService {
    @Autowired
    Gw2V1Client gw2V1Client;
    @Autowired
    Gw2V2Client gw2V2Client;

    public List<Match> getAllMatches() {
        final Map<Integer, String> worldNames = getWorldNames();

        final ArrayList<Match> matches = new ArrayList<Match>();
        for (JsonMatch gw2Match : gw2V1Client.getAllMatches().getJsonMatches()) {
            Match match = new Match(gw2Match.getId());
            match.getWorlds().add(new World(gw2Match.getBlueWorldId(), Colours.BLUE, worldNames.get(gw2Match.getBlueWorldId())));
            match.getWorlds().add(new World(gw2Match.getGreenWorldId(), Colours.GREEN, worldNames.get(gw2Match.getGreenWorldId())));
            match.getWorlds().add(new World(gw2Match.getRedWorldId(), Colours.RED, worldNames.get(gw2Match.getRedWorldId())));
            matches.add(match);
        }
        return matches;
    }

    public Map<Integer, String> getWorldNames() {
        final HashMap<Integer, String> map = new HashMap<>();
        final List<JsonWorld> allJsonWorlds = gw2V2Client.getAllWorlds("all", "fr");
        for (JsonWorld jsonWorld : allJsonWorlds) {
            map.put(jsonWorld.getId(), jsonWorld.getName());
        }
        return map;
    }
}
