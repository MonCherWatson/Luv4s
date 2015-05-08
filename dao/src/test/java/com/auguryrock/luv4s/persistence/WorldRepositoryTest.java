package com.auguryrock.luv4s.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext-derby.xml"})
public class WorldRepositoryTest {
    @Autowired
    WorldRepository worldRepository;

    @Test
    @Transactional
    public void testCrud() {
        World world = new World(1, Colour.Blue);
        worldRepository.save(world);

        final World one = worldRepository.findOne(1);

        assertThat(one).isNotNull();
        assertThat(one.getId()).isEqualTo(1);
        assertThat(one.getColour()).isEqualTo(Colour.Blue);
    }

    @AfterTransaction
    public void after() {
        assertThat(worldRepository.findOne(1)).isNull();
    }

}