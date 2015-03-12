package com.auguryrock.luv4s.services;

import com.auguryrock.luv4s.persistence.Match;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext-rest.xml", "/applicationContext-services.xml"})
public class MatchServiceTest {
    @Autowired
    MatchService matchService;

    @Test
    public void testGetAllMatches() throws Exception {
        final List<Match> allMatches = matchService.getAllMatches();
        System.out.println(allMatches);
    }

    @Test
    public void testGetWorldNames() throws Exception {

    }
}