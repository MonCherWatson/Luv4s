package com.auguryrock.luv4s.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by MonCherWatson on 22/05/2015.
 */
public interface TranslationRepository extends CrudRepository<Translation, Translation.TranslationId>, Repository<Translation, Translation.TranslationId> {
    List<Translation> findByLanguageOrderByNameKeyAsc(Language language);
}
