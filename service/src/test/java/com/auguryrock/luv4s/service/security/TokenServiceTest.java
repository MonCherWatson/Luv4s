package com.auguryrock.luv4s.service.security;

import com.auguryrock.luv4s.domain.scouting.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

/**
 * Created by MonCherWatson on 19/06/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = TokenServiceTest.TestContextConfiguration.class)
public class TokenServiceTest {
    @Autowired
    TokenService tokenService;

    @Test
    public void test()  {
        User user = new User();
        user.setName("foo");

        String s = tokenService.generateToken(user);
        System.out.println(s);
        String userFromToken = tokenService.getUserFromToken(s);

        assertThat("foo").isEqualTo(userFromToken);
    }

    @Configuration
    @ComponentScan(basePackages = {"com.auguryrock.luv4s.service.security"}, excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class))
    public static class  TestContextConfiguration {
    }
}