package com.auguryrock.luv4s.services;

import com.auguryrock.luv4s.persistence.ObjectiveDescription;
import com.auguryrock.luv4s.persistence.ObjectiveDescriptionRepository;
import com.auguryrock.luv4s.persistence.ObjectiveType;
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
    private ObjectiveDescriptionRepository objectiveRepository;
    @Autowired
    private JsonObjectiveDescriptionReader objectiveDescriptionReader;

    @Transactional
    public void createObjectivesDescription() {
        for (Map.Entry<String, JsonObjectiveDescription> o : objectiveDescriptionReader.getObjectiveDescriptions().entrySet()) {
            ObjectiveDescription objectiveDescription = new ObjectiveDescription();
            objectiveDescription.setId(Integer.valueOf(o.getKey()));
            JsonObjectiveDescription value = o.getValue();
            objectiveDescription.setName(value.getNames().get("en"));
            objectiveDescription.setType(ObjectiveType.valueOf(value.getType().toUpperCase()));
            objectiveRepository.save(objectiveDescription);
        }
    }
}
