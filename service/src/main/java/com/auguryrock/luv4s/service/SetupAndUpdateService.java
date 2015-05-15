package com.auguryrock.luv4s.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by Jose on 09/05/2015.
 */
@Component
public class SetupAndUpdateService {
    @Autowired
    private ObjectiveService objectiveService;
    @Autowired
    private MatchService matchService;

    @Transactional
    public void setupStaticData() {
        objectiveService.createObjectivesDescription();
    }

    @Transactional
    public void setupMatches() {
        matchService.createMatches();
    }

    @Transactional
    public void updateMatches() {
        matchService.createOrUpdateMaps();
    }
}
