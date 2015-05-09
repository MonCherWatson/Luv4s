package com.auguryrock.luv4s.services;

import com.auguryrock.luv4s.persistence.*;
import com.auguryrock.luv4s.rest.Gw2V1ClientMock;
import com.auguryrock.luv4s.rest.Gw2V2ClientMock;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext-rest.xml", "/applicationContext-services.xml", "/applicationContext-derby.xml"})
public class MatchServiceTest {
    @Resource
    private MatchService matchService;
    @Resource
    private MatchRepository matchRepository;
    @Resource
    private ObjectiveService objectiveService;


    @Before
    public void init() {
        matchService.gw2V1Client = new Gw2V1ClientMock();
        matchService.gw2V2Client = new Gw2V2ClientMock();
    }

    @Test
    @Transactional
    public void testGetAllMatches() throws Exception {
        final List<Match> allMatches = matchService.createMatches();

        assertThat(allMatches).hasSize(17);

        Match match = allMatches.get(0);
        assertThat(match.getId()).isEqualTo("2-3");
        checkWorlds(match);

        assertThat(match.getWvwMaps()).isEmpty();

    }

    @Test
    @Transactional
    public void testCreateOrUpdateObjectives() {
        //prerequisites
        matchService.createMatches();
        objectiveService.createObjectivesDescription();
        /////////////////

        matchService.createOrUpdateMaps();

        Match match = matchRepository.findOne("2-3");
        checkWorlds(match);

        assertThat(match.getWvwMaps()).hasSize(4);
        WvWMap greenMap = match.getWvwMaps().get(Colour.Green);

        assertThat(greenMap.getObjectives()).hasSize(18);

        ArrayList<Objective> objectives = Lists.newArrayList(greenMap.getObjectives());
        Objective objective = objectives.get(0);
        assertThat(objective.getMap()).isEqualTo(greenMap);
        assertThat(objective.getOwner()).isEqualTo(match.getWorlds().get(Colour.Green));
        assertThat(objective.getDescription()).isNotNull();
        assertThat(objective.getDescription().getId()).isEqualTo(48);

        objective = objectives.get(4);
        assertThat(objective.getMap()).isEqualTo(greenMap);
        assertThat(objective.getOwner()).isNull();
        assertThat(objective.getDescription()).isNotNull();
        assertThat(objective.getDescription().getId()).isEqualTo(75);
    }

    protected void checkWorlds(Match match) {
        assertThat(match.getWorlds()).hasSize(3).containsKeys(Colour.Blue, Colour.Red, Colour.Green);

        World world = match.getWorlds().get(Colour.Red);
        assertThat(world.getColour()).isEqualTo(Colour.Red);
        assertThat(world.getId()).isEqualTo(2103);
    }
}