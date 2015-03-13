package com.auguryrock.luv4s.services;

import com.auguryrock.luv4s.persistence.WvWMap;
import com.auguryrock.luv4s.persistence.WvWMapRepository;
import com.auguryrock.luv4s.persistence.WvWMatch;
import com.auguryrock.luv4s.rest.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class MapService {
    @Autowired
    protected Gw2V1Client gw2V1Client;
    @Autowired
    protected WvWMapRepository mapRepository;
    @Autowired
    protected JsonObjectiveDescriptionReader reader;

    public void buildMaps(Iterable<WvWMatch> matches) {
        try {
            final Map objectiveDescriptions = reader.getObjectiveDescriptions();


            for (WvWMatch match : matches) {
                final JsonMatchDetails matchDetails = gw2V1Client.getMatchDetails(match.getId());
                for (JsonMap jsonMap : matchDetails.getMaps()) {
                    final WvWMap map = new WvWMap();

                    for (JsonObjective objective : jsonMap.getJsonObjectives()) {
                        final Object o = objectiveDescriptions.get(Integer.valueOf(objective.getId()));
                        System.out.println(o);
                    }

                    mapRepository.save(map);

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }


    }
}
