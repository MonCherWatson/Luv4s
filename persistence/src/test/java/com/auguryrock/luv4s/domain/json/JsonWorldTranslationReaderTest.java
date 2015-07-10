package com.auguryrock.luv4s.domain.json;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by MonCherWatson on 22/05/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestContextConfiguration.class})
public class JsonWorldTranslationReaderTest extends TestCase {

    @Autowired
    JsonWorldTranslationReader reader;

    @Test
    public void test_read() {
        List<JsonWorldTranslation> translations = reader.getWorldTranslations();
        assertThat(translations).hasSize(51);
    }

}