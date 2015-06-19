package com.auguryrock.luv4s.service.security;

import com.auguryrock.luv4s.domain.scouting.*;
import com.auguryrock.luv4s.service.TestContextConfiguration;
import io.jsonwebtoken.JwtException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
public class SecurityServiceTest {
    @Autowired
    SecurityService securityService;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ScoutingKeyRepository scoutingKeyRepository;

    @Test
    public void testGoodToken()  {
        Player player = new Player();
        player.setName("foo");

        String s = securityService.generateJwt(player);
        String userFromToken = securityService.getPlayerNameFromToken(s);

        assertThat("foo").isEqualTo(userFromToken);
    }


    @Test(expected = JwtException.class)
    public void testWrongToken() {
        securityService.getPlayerNameFromToken("foo.foo.");
    }

    @Test(expected = RuntimeException.class)
    public void testWrongPlayerName() {
        securityService.getAuthorities("foo", "foo");
    }

    @Test
    public void testPlayerNoRole() {
        Player player = new Player();
        player.setName("scout");
        playerRepository.save(player);

        assertThat(securityService.getAuthorities("scout", "foo")).containsExactly(new SimpleGrantedAuthority(RoleType.basic.toString()));
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

        assertThat(securityService.getAuthorities("scout", "key")).containsExactly(new SimpleGrantedAuthority(RoleType.basic.toString()),
                new SimpleGrantedAuthority(RoleType.master.toString()));


    }


}