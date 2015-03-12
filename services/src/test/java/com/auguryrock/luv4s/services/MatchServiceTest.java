package com.auguryrock.luv4s.services;

import com.auguryrock.luv4s.persistence.WvWMatch;
import com.auguryrock.luv4s.persistence.WvWMatchRepository;
import com.auguryrock.luv4s.rest.Gw2V1Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext-rest.xml", "/applicationContext-services.xml", "/applicationContext-derby.xml"})
public class MatchServiceTest {
    @Resource
    private MatchService matchService;

    @Test
    public void testGetAllMatches() throws Exception {
        final List<WvWMatch> allMatches = matchService.createMatches();
        System.out.println(allMatches);
    }

    @Test
    public void testGetWorldNames() throws Exception {

    }
}