package com.auguryrock.luv4s.domain.json;

import com.auguryrock.luv4s.domain.TestContextConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {com.auguryrock.luv4s.domain.json.TestContextConfiguration.class})
public class JsonObjectiveDescriptionReaderTest {
    @Autowired
    JsonObjectiveDescriptionReader reader;

    @Test
    public void testGetObjectiveDescriptions() throws Exception {
        Map<String, JsonObjectiveDescription> objectiveDescriptions = reader.getObjectiveDescriptions();
        assertThat(objectiveDescriptions).hasSize(76);
    }
}