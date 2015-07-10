package com.auguryrock.luv4s.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestContextConfiguration.class})
public class Gw2V1ClientTest {
    @Autowired
    Gw2V1Client gw2V1Client;

    @Test
    public void testGetAllMatches() throws Exception {
        // https://api.guildwars2.com/v1/wvw/matches.json
        final JsonMatches allMatches = gw2V1Client.getAllMatches();
        assertThat(allMatches).isNotNull();
    }

    @Test
    public void testGetMatchDetails() throws Exception {
        // https://api.guildwars2.com/v1/wvw/match_details.json
        final String id = gw2V1Client.getAllMatches().getJsonMatches().get(0).getId();
        JsonMatchDetails matchDetails = gw2V1Client.getMatchDetails(id);
        assertThat(matchDetails.getMaps()).isNotNull().hasSize(4);
        assertThat(matchDetails.getScores()).isNotNull().hasSize(3);
    }


}