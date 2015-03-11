package com.auguryrock.luv4s.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext-rest.xml"})
public class Gw2V2ClientTest {
    @Autowired
    Gw2V2Client gw2V2Client;

    @Test
    public void test_get_words() {
        System.out.println(gw2V2Client.getAllWorlds("all", "fr"));
//        final Worlds allWorlds = gw2V2Client.getAllWorlds(null);
//        System.out.println(allWorlds);
    }

}