package com.auguryrock.luv4s.services;

import com.auguryrock.luv4s.persistence.Colours;
import com.auguryrock.luv4s.persistence.Match;
import com.auguryrock.luv4s.persistence.World;
import com.auguryrock.luv4s.persistence.WvWMap;
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
    Gw2V1Client gw2V1Client;
    @Autowired
    Gw2V2Client gw2V2Client;

    public List<Match> getAllMatches() {
        final Map<Integer, String> worldNames = getWorldNames();
        final ArrayList<Match> matches = new ArrayList<>();
        for (JsonMatch gw2Match : gw2V1Client.getAllMatches().getJsonMatches()) {
            Match match = new Match(gw2Match.getId());
            match.getWorlds().add(new World(gw2Match.getBlueWorldId(), Colours.Bleue, worldNames.get(gw2Match.getBlueWorldId())));
            match.getWorlds().add(new World(gw2Match.getGreenWorldId(), Colours.Green, worldNames.get(gw2Match.getGreenWorldId())));
            match.getWorlds().add(new World(gw2Match.getRedWorldId(), Colours.Red, worldNames.get(gw2Match.getRedWorldId())));
            matches.add(match);
        }
        return matches;
    }

    protected Map<Integer, String> getWorldNames() {
        final HashMap<Integer, String> map = new HashMap<>();
        final List<JsonWorld> allJsonWorlds = gw2V2Client.getAllWorlds("all", "fr");
        for (JsonWorld jsonWorld : allJsonWorlds) {
            map.put(jsonWorld.getId(), jsonWorld.getName());
        }
        return map;
    }

    public void getMatchDetails(String matchId) {
        final JsonMatchDetails matchDetails = gw2V1Client.getMatchDetails(matchId);
        for (JsonMap jsonMap : matchDetails.getMaps()) {
            WvWMap wvwMap = new WvWMap();
            wvwMap.setType(WvWMap.Type.valueOf(jsonMap.getType()));
            for (JsonObjective jsonObjective : jsonMap.getJsonObjectives()) {
            }
        }


    }
}
