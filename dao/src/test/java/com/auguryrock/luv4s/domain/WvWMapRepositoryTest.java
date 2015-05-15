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
public class WvWMapRepositoryTest {
    @Autowired
    WvWMapRepository mapRepository;
    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional
    public void test_crud() {
        WvWMap map = new WvWMap();
        final Objective objective = new Objective();
        objective.setMap(map);
        map.getObjectives().add(objective);
        mapRepository.save(map);

        assertThat(map.getPk()).isNotNull();

        em.flush();
        em.clear();

        final WvWMap one = mapRepository.findOne(map.getPk());
        assertThat(one).isEqualTo(map);
        assertThat(one.getObjectives()).hasSize(1);


    }

}