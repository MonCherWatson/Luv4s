package com.auguryrock.luv4s.services;

import com.auguryrock.luv4s.persistence.ObjectiveRepository;
import com.auguryrock.luv4s.rest.JsonObjectiveDescription;
import com.auguryrock.luv4s.rest.JsonObjectiveDescriptionReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Map;

/**
 * Created by Jose on 08/05/2015.
 */
@Component
public class ObjectiveService {
    @Autowired
    private ObjectiveRepository objectiveRepository;
    @Autowired
    private JsonObjectiveDescriptionReader objectiveDescriptionReader;

    @Transactional
    public void createObjectives() {
        for (Map.Entry<String, JsonObjectiveDescription> o : objectiveDescriptionReader.getObjectiveDescriptions().entrySet()) {

        }
    }
}
