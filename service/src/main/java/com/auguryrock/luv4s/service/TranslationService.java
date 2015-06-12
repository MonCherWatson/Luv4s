package com.auguryrock.luv4s.service;

import com.auguryrock.luv4s.domain.*;
import com.auguryrock.luv4s.domain.json.JsonObjectiveDescription;
import com.auguryrock.luv4s.domain.json.JsonWorldTranslation;
import com.auguryrock.luv4s.domain.json.JsonWorldTranslationReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by MonCherWatson on 22/05/2015.
 */
@Component
public class TranslationService {
    @Autowired
    private TranslationRepository translationRepository;
    @Autowired
    private JsonWorldTranslationReader translationReader;

    @Transactional
    public void createWorldTranslations() {
        List<Translation> entities = new ArrayList<>();
        List<JsonWorldTranslation> worldTranslations = translationReader.getWorldTranslations();
        for (JsonWorldTranslation json : worldTranslations) {
            entities.add(new Translation(World.WORLD + json.world_id, Language.de, json.name_de));
            entities.add(new Translation(World.WORLD + json.world_id, Language.en, json.name_en));
            entities.add(new Translation(World.WORLD + json.world_id, Language.fr, json.name_fr));
            entities.add(new Translation(World.WORLD + json.world_id, Language.es, json.name_es));
        }
        translationRepository.save(entities);
    }

    @Transactional void createObjectiveTranslation(String id, JsonObjectiveDescription json) {
        for (Map.Entry<String, String> entry : json.getNames().entrySet()) {
            Language lang = Language.valueOf(entry.getKey());
            Translation translation = new Translation(ObjectiveDescription.OBJECTIVE_DESCRIPTION + id, lang, entry.getValue());
            translationRepository.save(translation);
        }
    }

    @Transactional
    public List<Translation> getTranslationsByLanguage(Language language) {
        return translationRepository.findByLanguageOrderByNameKeyAsc(language);
    }


}
