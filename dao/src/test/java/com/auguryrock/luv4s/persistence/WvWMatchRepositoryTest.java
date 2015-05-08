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
public class WvWMatchRepositoryTest {
    @Autowired
    WvWMatchRepository wvwMatchRepository;
    @PersistenceContext
    EntityManager entityManager;


    @Test
    @Transactional
    public void test_crud() {
        final WvWMatch match = new WvWMatch("matchId");

        World world1 = new World(1, Colour.Blue);
        World world2 = new World(2, Colour.Red);
        World world3 = new World(3, Colour.Green);

        match.getWorlds().add(world1);
        match.getWorlds().add(world2);
        match.getWorlds().add(world3);
        wvwMatchRepository.save(match);

        entityManager.flush();
        entityManager.clear();

        final WvWMatch match1 = wvwMatchRepository.findOne("matchId");
        assertThat(match1).isNotNull();
        assertThat(match1.getWorlds()).hasSize(3).containsOnly(world3, world2, world1);
    }
}