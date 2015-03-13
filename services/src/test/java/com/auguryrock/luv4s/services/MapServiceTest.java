package com.auguryrock.luv4s.services;

import com.auguryrock.luv4s.persistence.WvWMap;
import com.auguryrock.luv4s.persistence.WvWMatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext-rest.xml", "/applicationContext-services.xml", "/applicationContext-derby.xml"})
public class MapServiceTest {
    @Autowired
    MapService mapService;


    @Test
    public void test_buildMaps() {
        final WvWMatch match = new WvWMatch();

        final List<WvWMap> maps = Arrays.asList(new WvWMap());
    }


}