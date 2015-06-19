package com.auguryrock.luv4s.service;

import com.auguryrock.luv4s.domain.scouting.*;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by MonCherWatson on 19/06/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = TestContextConfiguration.class)
@Transactional
public class PlayerServiceTest {
    @Autowired
    PlayerService playerService;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ScoutingKeyRepository scoutingKeyRepository;


    @Test(expected = RuntimeException.class)
    public void testWrongPlayerName() {
        playerService.getRoleType("foo", "foo");
    }

    @Test
    public void testPlayerNoRole() {
        Player player = new Player();
        player.setName("scout");
        playerRepository.save(player);

        assertThat(playerService.getRoleType("scout", "foo")).isEqualTo(RoleType.basic);
    }

    @Test
    public void testPlayerWithScoutingKey() {
        Player player = new Player();
        player.setName("scout");
        playerRepository.save(player);

        ScoutingKey scoutingKey = new ScoutingKey("key");
        scoutingKeyRepository.save(scoutingKey);

        Role role = new Role();
        role.setPlayer(player);
        role.setScoutingKey(scoutingKey);
        role.setRoleType(RoleType.master);
        roleRepository.save(role);

        assertThat(playerService.getRoleType("scout", "key")).isEqualTo(RoleType.master);


    }

}