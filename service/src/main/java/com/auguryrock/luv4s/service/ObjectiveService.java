package com.auguryrock.luv4s.service;

import com.auguryrock.luv4s.domain.*;
import com.auguryrock.luv4s.domain.json.JsonObjectiveDescription;
import com.auguryrock.luv4s.domain.json.JsonObjectiveDescriptionReader;
import com.auguryrock.luv4s.rest.JsonObjective;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Map;

/**
 * Created by MonCherWatson on 08/05/2015.
 */
@Component
public class ObjectiveService {
    final static Logger logger = LoggerFactory.getLogger(ObjectiveService.class);

    @Autowired
    private ObjectiveDescriptionRepository objectiveDescriptionRepository;
    @Autowired
    private JsonObjectiveDescriptionReader objectiveDescriptionReader;
    @Autowired
    private WorldRepository worldRepository;
    @Autowired
    private ObjectiveRepository objectiveRepository;
    @Autowired
    private TranslationService translationService;

    @Transactional
    public void createObjectivesDescription() {

        if (objectiveRepository.count() != 0) {
            return;
        }
        for (Map.Entry<String, JsonObjectiveDescription> entry : objectiveDescriptionReader.getObjectiveDescriptions().entrySet()) {
            String key = entry.getKey();
            JsonObjectiveDescription value = entry.getValue();

            ObjectiveDescription objectiveDescription = new ObjectiveDescription();
            objectiveDescription.setId(Integer.valueOf(key));
            objectiveDescription.setNameKey(ObjectiveDescription.OBJECTIVE_DESCRIPTION + key);
            objectiveDescription.setType(ObjectiveType.valueOf(value.getType().toUpperCase()));
            objectiveDescriptionRepository.save(objectiveDescription);

            translationService.createObjectiveTranslation(key, value);
        }
        logger.info(objectiveDescriptionRepository.count() + " Objective Descriptions have been saved.");
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
        World world = map.getMatchup().getWorlds().get(colour);
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
