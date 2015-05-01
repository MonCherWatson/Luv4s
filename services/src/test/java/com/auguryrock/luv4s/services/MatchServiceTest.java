package com.auguryrock.luv4s.services;

import com.auguryrock.luv4s.persistence.Colours;
import com.auguryrock.luv4s.persistence.World;
import com.auguryrock.luv4s.persistence.WvWMatch;
import com.auguryrock.luv4s.persistence.WvWMatchRepository;
import com.auguryrock.luv4s.rest.Gw2V1ClientMock;
import com.auguryrock.luv4s.rest.Gw2V2ClientMock;
import org.assertj.core.api.Assertions;
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

        World world = wvWMatch.getWorlds().iterator().next();
        assertThat(world.getColour()).isEqualTo(Colours.Blue);
        assertThat(world.getId()).isEqualTo(Colours.Blue);

//        System.out.println(allMatches);
//        for (WvWMatch match : wvWMatchRepository.findAll()) {
//            System.out.println("matchId=" + match.getId());
//            for (World world : match.getWorlds()) {
//                System.out.println("\tworldId=" + world.getId());
//
//            }
//            System.out.println("******************************");
//        }
    }

    @Test
    public void testGetWorldNames() throws Exception {

    }
}