package com.auguryrock.luv4s.services;

import com.auguryrock.luv4s.domain.ObjectiveDescription;
import com.auguryrock.luv4s.domain.ObjectiveDescriptionRepository;
import com.auguryrock.luv4s.domain.ObjectiveType;
import com.google.common.collect.Lists;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Jose on 08/05/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext-rest.xml", "/applicationContext-services.xml", "/applicationContext-derby.xml"})
public class ObjectiveServiceTest extends TestCase {
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
        assertThat(description.getName()).isEqualTo("Overlook");



    }
}