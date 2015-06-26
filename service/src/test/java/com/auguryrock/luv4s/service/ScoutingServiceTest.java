package com.auguryrock.luv4s.service;

import com.auguryrock.luv4s.domain.Colour;
import com.auguryrock.luv4s.domain.World;
import com.auguryrock.luv4s.domain.WorldRepository;
import com.auguryrock.luv4s.domain.scouting.*;
import com.auguryrock.luv4s.service.security.JwtAuthentication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by MonCherWatson on 26/06/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = TestContextConfiguration.class)
@Transactional
public class ScoutingServiceTest {
    @Autowired
    private ScoutingService scoutingService;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private WorldRepository worldRepository;

    private Player player;

    public void init() {
        player = new Player();
        player.setName("foo");
        playerRepository.save(player);
        SecurityContextHolder.getContext().setAuthentication(new JwtAuthentication(player, Collections.emptyList()));
    }

    @Test
    public void testCreateNewScoutingKey() throws Exception {
        init();
        World world = new World(666, Colour.Blue);
        worldRepository.save(world);

        ScoutingKey newScoutingKey = scoutingService.createScoutingKey(world.getId());

        Role role = roleRepository.findByPlayerAndScoutingKey(player, newScoutingKey.getUuid());

        assertThat(role.getRoleType()).isEqualTo(RoleType.master);

    }
}