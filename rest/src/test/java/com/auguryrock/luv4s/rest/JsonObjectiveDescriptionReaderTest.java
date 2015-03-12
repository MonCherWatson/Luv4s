package com.auguryrock.luv4s.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext-rest.xml"})
public class JsonObjectiveDescriptionReaderTest {
    @Autowired
    JsonObjectiveDescriptionReader reader;

    @Test
    public void testGetObjectiveDescriptions() throws Exception {
        final Map descriptions = reader.getObjectiveDescriptions();
        System.out.println(descriptions);
    }
}