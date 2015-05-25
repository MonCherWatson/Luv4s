package com.auguryrock.luv4s.service;

import com.auguryrock.luv4s.domain.Language;
import com.auguryrock.luv4s.domain.Translation;
import com.auguryrock.luv4s.domain.TranslationRepository;
import com.auguryrock.luv4s.domain.World;
import com.auguryrock.luv4s.domain.json.JsonWorldTranslation;
import com.auguryrock.luv4s.domain.json.JsonWorldTranslationReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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


}
