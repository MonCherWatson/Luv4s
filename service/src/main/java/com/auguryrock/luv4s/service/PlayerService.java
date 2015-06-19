package com.auguryrock.luv4s.service;

import com.auguryrock.luv4s.domain.scouting.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by MonCherWatson on 19/06/2015.
 */
@Component
public class PlayerService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PlayerRepository playerRepository;

    public RoleType getRoleType(String username, String scoutingKey) {
        Player player = playerRepository.findByName(username);
        if (player == null) {
            throw new RuntimeException("Player doesnt exist: " + username + "!");
        }

        Role role = roleRepository.findByUserAndScoutingKey(username, scoutingKey);
        if (role != null) {
            return role.getRoleType();
        }
        return RoleType.basic;
    }
}
