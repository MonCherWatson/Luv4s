package com.auguryrock.luv4s.service;

import com.auguryrock.luv4s.domain.World;
import com.auguryrock.luv4s.domain.WorldRepository;
import com.auguryrock.luv4s.domain.scouting.*;
import com.auguryrock.luv4s.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

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
    public List<ScoutingSession> findScoutingSessions(String scoutingKey, String objectiveId) {
        return null;
    }


//    @Transactional
//    @PreAuthorize("isFullyAuthenticated()")
//    public ScoutingSession createScoutingSession() {
//
//    }


}
