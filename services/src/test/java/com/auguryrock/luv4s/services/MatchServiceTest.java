package com.auguryrock.luv4s.services;

import com.auguryrock.luv4s.persistence.*;
import com.auguryrock.luv4s.rest.Gw2V1ClientMock;
import com.auguryrock.luv4s.rest.Gw2V2ClientMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext-rest.xml", "/applicationContext-services.xml", "/applicationContext-derby.xml"})
public class MatchServiceTest {
    @Resource
    private MatchService matchService;
    @Resource
    WvWMatchRepository wvWMatchRepository;


    @Before
    public void init() {
        matchService.gw2V1Client = new Gw2V1ClientMock();
        matchService.gw2V2Client = new Gw2V2ClientMock();
    }

    @Test
    @Transactional
    public void testGetAllMatches() throws Exception {
        final List<WvWMatch> allMatches = matchService.createMatches();

        assertThat(allMatches).hasSize(17);

        WvWMatch wvWMatch = allMatches.get(0);
        assertThat(wvWMatch.getId()).isEqualTo("2-3");
        assertThat(wvWMatch.getWorlds()).hasSize(3);

        World world = wvWMatch.getWorlds().get(Colour.Red);
        assertThat(world.getColour()).isEqualTo(Colour.Red);
        assertThat(world.getId()).isEqualTo(2103);

    }

    @Test
    @Transactional
    public void testCreateOrUpdateObjectives() {
        matchService.createMatches();
        List<WvWMatch> matches = matchService.createOrUpdateMaps();
        WvWMatch match = matches.get(0);
        assertThat(match.getId()).isEqualTo("2-3");
        assertThat(match.getWvwMaps()).hasSize(3).containsKeys(Colour.Blue, Colour.Red, Colour.Green);

        WvWMap greenMap = match.getWvwMaps().get(Colour.Green);

//        assertThat(greenMap.getStructures()).hasSize(3);


    }

    @Test
    public void testGetWorldNames() throws Exception {

    }
}