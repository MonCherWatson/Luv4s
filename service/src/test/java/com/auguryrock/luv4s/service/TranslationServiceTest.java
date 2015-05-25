package com.auguryrock.luv4s.service;

import com.auguryrock.luv4s.domain.Translation;
import com.auguryrock.luv4s.domain.TranslationRepository;
import junit.framework.TestCase;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by MonCherWatson on 22/05/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = TestContextConfiguration.class)
@Transactional
public class TranslationServiceTest {
    @Autowired
    TranslationService translationService;
    @Autowired
    TranslationRepository translationRepository;

    @Test
    public void testCreateWorldTranslations() throws Exception {
        translationService.createWorldTranslations();

        Iterable<Translation> all = translationRepository.findAll();

        assertThat(all).isNotEmpty();
    }
}