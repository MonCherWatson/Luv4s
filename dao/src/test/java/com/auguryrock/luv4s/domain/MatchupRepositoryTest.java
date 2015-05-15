package com.auguryrock.luv4s.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext-derby.xml"})
public class MatchupRepositoryTest {
    @Autowired
    MatchupRepository matchupRepository;
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    WorldRepository worldRepository;


    @Test
    @Transactional
    public void test_crud() {
        final Matchup matchup = new Matchup("matchId");

        World world1 = new World(1, Colour.Blue);
        World world2 = new World(2, Colour.Red);
        World world3 = new World(3, Colour.Green);

        matchup.addWorld(world1);
        matchup.addWorld(world2);
        matchup.addWorld(world3);
        matchupRepository.save(matchup);

        entityManager.flush();
        entityManager.clear();

        Iterable<World> all = worldRepository.findAll();
        assertThat(all).hasSize(3);
        assertThat(all.iterator().next().getMatchup()).isNotNull();

        final Matchup matchup1 = matchupRepository.findOne("matchId");
        assertThat(matchup1).isNotNull();
        assertThat(matchup1.getWorlds()).hasSize(3).containsKeys(Colour.Blue, Colour.Green, Colour.Red).containsValues(world3, world2, world1);
    }
}