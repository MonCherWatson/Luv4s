package com.auguryrock.luv4s.services;

import com.auguryrock.luv4s.persistence.World;
import com.auguryrock.luv4s.persistence.WvWMatch;
import com.auguryrock.luv4s.persistence.WvWMatchRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext-rest.xml", "/applicationContext-services.xml", "/applicationContext-derby.xml"})
public class MatchServiceTest {
    @Resource
    private MatchService matchService;
    @Resource
    WvWMatchRepository wvWMatchRepository;

    @Test
    @Transactional
    public void testGetAllMatches() throws Exception {
        final List<WvWMatch> allMatches = matchService.createMatches();
        System.out.println(allMatches);
        for (WvWMatch match : wvWMatchRepository.findAll()) {
            System.out.println("matchId=" + match.getId());
            for (World world : match.getWorlds()) {
                System.out.println("\tworldId=" + world.getId());

            }
            System.out.println("******************************");
        }
    }

    @Test
    public void testGetWorldNames() throws Exception {

    }
}