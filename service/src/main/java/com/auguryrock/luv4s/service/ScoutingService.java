package com.auguryrock.luv4s.service;

import com.auguryrock.luv4s.domain.Objective;
import com.auguryrock.luv4s.domain.ObjectiveRepository;
import com.auguryrock.luv4s.domain.World;
import com.auguryrock.luv4s.domain.WorldRepository;
import com.auguryrock.luv4s.domain.scouting.*;
import com.auguryrock.luv4s.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import javax.persistence.ManyToOne;
import javax.transaction.Transactional;
import javax.ws.rs.QueryParam;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
public class ScoutingService {
    @Autowired
    private SecurityService securityService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ScoutingKeyRepository scoutingKeyRepository;
    @Autowired
    private WorldRepository worldRepository;
    @Autowired
    private ObjectiveRepository objectiveRepository;
    @Autowired
    private ScoutingSessionRepository scoutingSessionRepository;

    @Transactional
    @PreAuthorize("isFullyAuthenticated()")
    public ScoutingKey createScoutingKey(Integer wordId) {
        World world = worldRepository.findOne(wordId);

        Player player = securityService.getCurrentPlayer();

        ScoutingKey scoutingKey = new ScoutingKey(world);
        scoutingKeyRepository.save(scoutingKey);

        Role role = new Role();
        role.setPlayer(player);
        role.setScoutingKey(scoutingKey);
        role.setRoleType(RoleType.master);
        roleRepository.save(role);

        return scoutingKey;
    }


    @Transactional
    @PreAuthorize("isFullyAuthenticated()")
    public List<ScoutingSession> findScoutingSessions(String scoutingKey, Integer objectiveId) {
        return scoutingSessionRepository.findByObjectiveAndScoutingKeyAnd(objectiveId, scoutingKey);
    }


    @Transactional
    @PreAuthorize("isFullyAuthenticated()")
    public ScoutingSession createScoutingSession(Date start,
                                                 Date end,
                                                 String description,
                                                 String scoutingKeyUuid,
                                                 Integer objectivePk) {
        Player player = securityService.getCurrentPlayer();
        Objective objective = objectiveRepository.findOne(objectivePk);
        ScoutingKey scoutingKey = scoutingKeyRepository.findOne(scoutingKeyUuid);

        ScoutingSession scoutingSession = new ScoutingSession(start, end, description);
        scoutingSession.setPlayer(player);
        scoutingSession.setObjective(objective);
        scoutingSession.setScoutingKey(scoutingKey);

        scoutingSessionRepository.save(scoutingSession);
        return scoutingSession;
    }


}
