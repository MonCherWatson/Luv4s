package com.auguryrock.luv4s.service;

import com.auguryrock.luv4s.domain.*;
import com.auguryrock.luv4s.rest.*;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestContextConfiguration.class})
@Transactional
public class MatchupServiceTest {

    @Resource
    private MatchService matchService;
    @Resource
    private MatchupRepository matchupRepository;
    @Resource
    private ObjectiveService objectiveService;


    @Test
    public void testGetAllMatches() throws Exception {
        final List<Matchup> allMatchups = matchService.createMatches();

        assertThat(allMatchups).hasSize(17);

        Matchup matchup = allMatchups.get(0);
        assertThat(matchup.getId()).isEqualTo("2-3");
        checkWorlds(matchup);

        assertThat(matchup.getWvwMaps()).isEmpty();

    }

    @Test
    public void testCreateOrUpdateObjectives() {
        //prerequisites
        matchService.createMatches();
        objectiveService.createObjectivesDescription();
        /////////////////

        matchService.createOrUpdateMaps();

        Matchup matchup = matchupRepository.findOne("2-3");
        checkWorlds(matchup);

        assertThat(matchup.getWvwMaps()).hasSize(4);
        WvWMap greenMap = matchup.getWvwMaps().get(Colour.Green);

        assertThat(greenMap.getObjectives()).hasSize(18);

        ArrayList<Objective> objectives = Lists.newArrayList(greenMap.getObjectives());
        Objective objective = objectives.get(0);
        assertThat(objective.getMap()).isEqualTo(greenMap);
        assertThat(objective.getOwner()).isEqualTo(matchup.getWorlds().get(Colour.Green));
        assertThat(objective.getDescription()).isNotNull();
        assertThat(objective.getDescription().getId()).isEqualTo(48);

        objective = objectives.get(4);
        assertThat(objective.getMap()).isEqualTo(greenMap);
        assertThat(objective.getOwner()).isNull();
        assertThat(objective.getDescription()).isNotNull();
        assertThat(objective.getDescription().getId()).isEqualTo(75);
    }

    protected void checkWorlds(Matchup matchup) {
        assertThat(matchup.getWorlds()).hasSize(3).containsKeys(Colour.Blue, Colour.Red, Colour.Green);

        World world = matchup.getWorlds().get(Colour.Red);
        assertThat(world.getColour()).isEqualTo(Colour.Red);
        assertThat(world.getId()).isEqualTo(2103);
    }
}