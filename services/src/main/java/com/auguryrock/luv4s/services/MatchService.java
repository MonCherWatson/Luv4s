package com.auguryrock.luv4s.services;

import com.auguryrock.luv4s.domain.Colours;
import com.auguryrock.luv4s.domain.Map;
import com.auguryrock.luv4s.domain.Match;
import com.auguryrock.luv4s.domain.World;
import com.auguryrock.luv4s.rest.Gw2WvWClient;
import com.auguryrock.luv4s.rest.WvWMatch;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by p127638 on 18.02.2015.
 */
public class MatchService {
    @Autowired
    Gw2WvWClient restClient;

    public List<Match> getAllMatches() {
        final ArrayList<Match> matches = new ArrayList<Match>();

        for(WvWMatch gw2Match : restClient.getAllMatches().getWvWMatches()) {
            Match match = new Match(gw2Match.getId());
            match.getWorlds().add(new World(gw2Match.getBlueWorldId(), Colours.BLUE));
            match.getWorlds().add(new World(gw2Match.getGreenWorldId(), Colours.GREEN));
            match.getWorlds().add(new World(gw2Match.getRedWorldId(), Colours.RED));

            matches.add(match);
        }

        return matches;
    }
}
