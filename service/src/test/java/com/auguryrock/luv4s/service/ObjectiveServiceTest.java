package com.auguryrock.luv4s.service;

import com.auguryrock.luv4s.domain.ObjectiveDescription;
import com.auguryrock.luv4s.domain.ObjectiveDescriptionRepository;
import com.auguryrock.luv4s.domain.ObjectiveType;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by MonCherWatson on 08/05/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = TestContextConfiguration.class)
public class ObjectiveServiceTest {

    @Autowired
    private ObjectiveService objectiveService;
    @Autowired
    private ObjectiveDescriptionRepository repository;

    @Test
    @Transactional
    public void testCreateObjectivesDescription() {
        objectiveService.createObjectivesDescription();
        List<ObjectiveDescription> all = Lists.newArrayList(repository.findAll());
        assertThat(all).hasSize(76);
        ObjectiveDescription description = repository.findOne(1);
        assertThat(description.getId()).isEqualTo(1);
        assertThat(description.getType()).isEqualTo(ObjectiveType.KEEP);
        assertThat(description.getNameKey()).isEqualTo("objectiveDescription1");
    }
}