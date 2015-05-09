package com.auguryrock.luv4s.services;

import com.auguryrock.luv4s.persistence.*;
import com.auguryrock.luv4s.rest.JsonObjective;
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
    private ObjectiveDescriptionRepository objectiveDescriptionRepository;
    @Autowired
    private JsonObjectiveDescriptionReader objectiveDescriptionReader;
    @Autowired
    private WorldRepository worldRepository;
    @Autowired
    private ObjectiveRepository objectiveRepository;

    @Transactional
    public void createObjectivesDescription() {
        for (Map.Entry<String, JsonObjectiveDescription> o : objectiveDescriptionReader.getObjectiveDescriptions().entrySet()) {
            ObjectiveDescription objectiveDescription = new ObjectiveDescription();
            objectiveDescription.setId(Integer.valueOf(o.getKey()));
            JsonObjectiveDescription value = o.getValue();
            objectiveDescription.setName(value.getNames().get("en"));
            objectiveDescription.setType(ObjectiveType.valueOf(value.getType().toUpperCase()));
            objectiveDescriptionRepository.save(objectiveDescription);
        }
    }

    @Transactional
    public void createOrUpdateObjective(JsonObjective jsonObjective, WvWMap map) {
        Objective objective = findObjectiveInSet(map, jsonObjective.getId());
        if (objective == null) {
            objective = new Objective();
            ObjectiveDescription description = objectiveDescriptionRepository.findOne(jsonObjective.getId());
            objective.setDescription(description);
            objectiveRepository.save(objective);
            map.addOjective(objective);
        }
        Colour colour = Colour.valueOf(jsonObjective.getOwner());
        World world = map.getMatch().getWorlds().get(colour);
        objective.setOwner(world);
    }

    protected Objective findObjectiveInSet(WvWMap map, Integer descriptionId) {
        assert descriptionId != null;
        for (Objective objective : map.getObjectives()) {
            if (objective.getDescription().getId().equals(descriptionId)) {
                return objective;
            }
        }
        return null;
    }

}
