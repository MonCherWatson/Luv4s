package com.auguryrock.luv4s.rest;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestContextConfiguration.class})
public class JsonObjectiveDescriptionReaderTest {
    @Autowired
    JsonObjectiveDescriptionReader reader;

    @Test
    public void testGetObjectiveDescriptions() throws Exception {
        Map<String, JsonObjectiveDescription> objectiveDescriptions = reader.getObjectiveDescriptions();
        assertThat(objectiveDescriptions).hasSize(76);
    }
}