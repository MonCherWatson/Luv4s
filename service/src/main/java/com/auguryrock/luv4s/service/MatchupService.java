package com.auguryrock.luv4s.service;

import com.auguryrock.luv4s.domain.*;
import com.auguryrock.luv4s.rest.*;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MatchupService {
    private final static Logger logger = LoggerFactory.getLogger(MatchupService.class);

    @Resource(name = "gw2V1Client")
    protected Gw2V1Client gw2V1Client;
    @Resource(name = "gw2V2Client")
    protected Gw2V2Client gw2V2Client;
    @Autowired
    private MatchupRepository matchupRepository;
    @Autowired
    private WvWMapRepository mapRepository;
    @Autowired
    private ObjectiveService objectiveService;

    @Transactional
    public List<Matchup> createMatches() {
        final ArrayList<Matchup> matchups = new ArrayList<>();
        for (JsonMatch gw2Match : gw2V1Client.getAllMatches().getJsonMatches()) {
            Matchup matchup = new Matchup(gw2Match.getId());
            matchup.addWorld(new World(gw2Match.getBlueWorldId(), Colour.Blue));
            matchup.addWorld(new World(gw2Match.getGreenWorldId(), Colour.Green));
            matchup.addWorld(new World(gw2Match.getRedWorldId(), Colour.Red));
            if (gw2Match.getId().startsWith("2")) {
                matchup.setZone(Zone.EU);
            } else {
                matchup.setZone(Zone.US);
            }
            matchup = matchupRepository.save(matchup);
            matchups.add(matchup);
        }
        logger.info(matchupRepository.count() + " matchups have been created.");
        return matchups;
    }

    @Transactional
    public List<Matchup> createOrUpdateMaps() {
        List<Matchup> matchups = Lists.newArrayList(matchupRepository.findAll());
        for (Matchup matchup : matchups) {
            JsonMatchDetails matchDetails = gw2V1Client.getMatchDetails(matchup.getId());
            updateScores(matchDetails.getScores(), matchup.getWorlds());
            for (JsonMap jsonMap : matchDetails.getMaps()) {
                WvWMap map = createOrUpdateMap(jsonMap, matchup);
                if (map != null) {
                    for (JsonObjective jsonObjective : jsonMap.getJsonObjectives()) {
                        objectiveService.createOrUpdateObjective(jsonObjective, map);
                    }
                }
            }
            matchupRepository.save(matchup);
        }
        return matchups;
    }

    @Transactional
    public List<Matchup> getCurrentMatches() {
        return Lists.newArrayList(matchupRepository.findAll());
    }

    @Transactional
    public List<Matchup> getCurrentMatchesByZone(Zone zone) {
        return Lists.newArrayList(matchupRepository.findByZoneOrderByIdAsc(zone));
    }

    @Transactional
    public Matchup getMatch(String id) {
        return matchupRepository.findOne(id);
    }


    protected WvWMap createOrUpdateMap(JsonMap jsonMap, Matchup matchup) {
        Colour mapColour = convertMapTypeColour(jsonMap.getType());
        WvWMap wvwWMap = matchup.getWvwMaps().get(mapColour);
        if (wvwWMap == null) {
            wvwWMap = new WvWMap(mapColour);
            matchup.addMaps(wvwWMap);
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
        worlds.get(Colour.Blue).setScore(scores.get(1));
        worlds.get(Colour.Green).setScore(scores.get(2));
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
