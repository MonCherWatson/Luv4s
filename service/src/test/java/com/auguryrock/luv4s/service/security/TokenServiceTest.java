package com.auguryrock.luv4s.service.security;

import com.auguryrock.luv4s.domain.scouting.Player;
import com.auguryrock.luv4s.rest.Gw2V1Client;
import com.auguryrock.luv4s.rest.Gw2V1ClientMock;
import com.auguryrock.luv4s.rest.Gw2V2Client;
import com.auguryrock.luv4s.rest.Gw2V2ClientMock;
import io.jsonwebtoken.JwtException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by MonCherWatson on 19/06/2015.
 */
public class TokenServiceTest {
    TokenService tokenService;

    @Before
    public void init() throws IOException, ClassNotFoundException {
        tokenService = new TokenService();
        tokenService.init();
    }

    @Test
    public void testGoodToken()  {
        Player player = new Player();
        player.setName("foo");

        String s = tokenService.generateToken(player);
        String userFromToken = tokenService.getPlayerNameFromToken(s);

        assertThat("foo").isEqualTo(userFromToken);
    }


    @Test(expected = JwtException.class)
    public void testWrongToken() {
        String userFromToken = tokenService.getPlayerNameFromToken("foo.foo.");
    }

}