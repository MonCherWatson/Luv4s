package com.auguryrock.luv4s.rest;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext-rest.xml"})
public class Gw2WvWClientTest {
    @Autowired
    Gw2WvWClient gw2WvWClient;

    @Test
    public void testGetAllMatches() throws Exception {
        // https://api.guildwars2.com/v1/wvw/matches.json
        gw2WvWClient.getAllMatches();
    }

    @Test
    public void testGetMatchDetails() throws Exception {
        // https://api.guildwars2.com/v1/wvw/match_details.json
        final String id = gw2WvWClient.getAllMatches().getWvWMatches().get(0).getId();
        gw2WvWClient.getMatchDetails(id);
    }

}