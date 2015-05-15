package com.auguryrock.luv4s.rest;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestContextConfiguration.class})
public class Gw2V2ClientTest {
    @Autowired
    Gw2V2Client gw2V2Client;

    @Test
    public void test_get_words() {
        List<JsonWorld> worlds = gw2V2Client.getWorlds("all", "fr");
        Assertions.assertThat(worlds).isNotNull();
    }

}