package com.auguryrock.luv4s;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by MonCherWatson on 10/07/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Luv4sApplication.class)
public class Luv4sApplicationTestCase {
    @Resource(name = "customAuthenticationManager")
    private AuthenticationManager authenticationManager;


    @Test
    public void comeOnBaby() {
        //Integration test
//        System.out.println(authenticationManager);
    }
}
