package com.auguryrock.luv4s.domain;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

/**
 * Created by MonCherWatson on 29/05/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestContextConfiguration.class})
public class TranslationRepositoryTest extends TestCase {
    @Autowired
    private TranslationRepository repository;

    @Test
    public void testFindByLanguageOrderByKeyAsc() throws Exception {
        Translation translation = new Translation("key", Language.de, "dumkoff");
        repository.save(translation);

        List<Translation> list = repository.findByLanguageOrderByNameKeyAsc(Language.de);
    }
}