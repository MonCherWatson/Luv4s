package com.auguryrock.luv4s.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

/**
 * Created by Jose on 09/05/2015.
 */
@Component
public class SetupAndUpdateService {
    final static Logger logger = LoggerFactory.getLogger(SetupAndUpdateService.class);

    @Autowired
    private ObjectiveService objectiveService;
    @Autowired
    private MatchupService matchupService;

    @PostConstruct
    @Transactional
    public void setup() {
        logger.info("*********************ENTERING METHOD SETUP!");
        objectiveService.createObjectivesDescription();
        matchupService.createMatches();
    }

    @Transactional
    @Scheduled(fixedRate = 60000)
    public void updateMatches() {
        matchupService.createOrUpdateMaps();
    }
}
