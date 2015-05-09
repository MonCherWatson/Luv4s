package com.auguryrock.luv4s.persistence;

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
public class MatchRepositoryTest {
    @Autowired
    MatchRepository matchRepository;
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    WorldRepository worldRepository;


    @Test
    @Transactional
    public void test_crud() {
        final Match match = new Match("matchId");

        World world1 = new World(1, Colour.Blue);
        World world2 = new World(2, Colour.Red);
        World world3 = new World(3, Colour.Green);

        match.addWorld(world1);
        match.addWorld(world2);
        match.addWorld(world3);
        matchRepository.save(match);

        entityManager.flush();
        entityManager.clear();

        Iterable<World> all = worldRepository.findAll();
        assertThat(all).hasSize(3);
        assertThat(all.iterator().next().getMatch()).isNotNull();

        final Match match1 = matchRepository.findOne("matchId");
        assertThat(match1).isNotNull();
        assertThat(match1.getWorlds()).hasSize(3).containsKeys(Colour.Blue, Colour.Green, Colour.Red).containsValues(world3, world2, world1);
    }
}