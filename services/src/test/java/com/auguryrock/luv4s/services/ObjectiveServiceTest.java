package com.auguryrock.luv4s.services;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

/**
 * Created by Jose on 08/05/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext-rest.xml", "/applicationContext-services.xml", "/applicationContext-derby.xml"})
public class ObjectiveServiceTest extends TestCase {
    @Autowired
    private ObjectiveService objectiveService;

    @Test
    @Transactional
    public void testCreateObjectives() {
        objectiveService.createObjectives();
    }
}