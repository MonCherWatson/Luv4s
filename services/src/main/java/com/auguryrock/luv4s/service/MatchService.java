package com.auguryrock.luv4s.service;

import com.auguryrock.luv4s.domain.*;
import com.auguryrock.luv4s.rest.*;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;

@Component
public class MatchService {
    @Autowired
    protected Gw2V1Client gw2V1Client;
    @Autowired
    protected Gw2V2Client gw2V2Client;
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private WvWMapRepository mapRepository;
    @Autowired
    private ObjectiveService objectiveService;

    public List<Match> createMatches() {
        final ArrayList<Match> matches = new ArrayList<>();
        for (JsonMatch gw2Match : gw2V1Client.getAllMatches().getJsonMatches()) {
            Match match = new Match(gw2Match.getId());
            match.getWorlds().put(Colour.Blue, new World(gw2Match.getBlueWorldId(), Colour.Blue));
            match.getWorlds().put(Colour.Green, new World(gw2Match.getGreenWorldId(), Colour.Green));
            match.getWorlds().put(Colour.Red, new World(gw2Match.getRedWorldId(), Colour.Red));
            if (gw2Match.getId().startsWith("2")) {
                match.setZone(Zone.EU);
            } else {
                match.setZone(Zone.US);
            }
            match = matchRepository.save(match);
            matches.add(match);
        }
        return matches;
    }

    public List<Match> createOrUpdateMaps() {
        List<Match> matches = Lists.newArrayList(matchRepository.findAll());
        for (Match match : matches) {
            JsonMatchDetails matchDetails = gw2V1Client.getMatchDetails(match.getId());
            updateScores(matchDetails.getScores(), match.getWorlds());
            for (JsonMap jsonMap : matchDetails.getMaps()) {
                WvWMap map = createOrUpdateMap(jsonMap, match);
                if (map != null) {
                    for (JsonObjective jsonObjective : jsonMap.getJsonObjectives()) {
                        objectiveService.createOrUpdateObjective(jsonObjective, map);
                    }
                }
            }
            matchRepository.save(match);
        }
        return matches;
    }


    protected WvWMap createOrUpdateMap(JsonMap jsonMap, Match match) {
        Colour mapColour = convertMapTypeColour(jsonMap.getType());
        WvWMap wvwWMap = match.getWvwMaps().get(mapColour);
        if (wvwWMap == null) {
            wvwWMap = new WvWMap(mapColour);
            match.addMaps(wvwWMap);
        }
        return wvwWMap;
    }

    protected Colour convertMapTypeColour(JsonMap.Type mapType) {
        switch (mapType) {
            case RedHome:
                return Colour.Red;
            case GreenHome:
                return Colour.Green;
            case BlueHome:
                return Colour.Blue;
            case Center:
                return Colour.Neutral;
            default:
                throw new RuntimeException("Unknown map type: " + mapType);
        }
    }

    protected void updateScores(List<Integer> scores, Map<Colour, World> worlds) {
        worlds.get(Colour.Red).setScore(scores.get(0));
        worlds.get(Colour.Green).setScore(scores.get(1));
        worlds.get(Colour.Blue).setScore(scores.get(2));
    }

    protected Map<Integer, String> getWorldNames(String lang) {
        final HashMap<Integer, String> map = new HashMap<>();
        final List<JsonWorld> allJsonWorlds = gw2V2Client.getWorlds("all", lang);
        for (JsonWorld jsonWorld : allJsonWorlds) {
            map.put(jsonWorld.getId(), jsonWorld.getName());
        }
        return map;
    }

    @Transactional
    public List<Match> getCurrentMatches() {
        return Lists.newArrayList(matchRepository.findAll());

    }
}
