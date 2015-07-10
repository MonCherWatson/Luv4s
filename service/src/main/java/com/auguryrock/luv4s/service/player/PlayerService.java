package com.auguryrock.luv4s.service.player;

import com.auguryrock.luv4s.domain.scouting.Player;
import com.auguryrock.luv4s.domain.scouting.PlayerRepository;
import com.auguryrock.luv4s.domain.scouting.RoleRepository;
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

    public Player findByName(String name) {
        return playerRepository.findByName(name);
    }

    public Player findByNameAndPassword(String name, String password) {
        return playerRepository.findByName(name);
    }

    public PlayerCreation createPlayer(Player player) {
        PlayerCreation playerCreation = new PlayerCreation();

        checkNullValue(playerCreation, player.getAccountId(), "accountId");
        checkNullValue(playerCreation, player.getPassword(), "password");
        checkNullValue(playerCreation, player.getName(), "name");

        if (playerRepository.findByName(player.getName()) != null) {
            alreadyExists(playerCreation, "name");
        }
        if (playerRepository.findByAccountId(player.getAccountId()) != null) {
            alreadyExists(playerCreation, "accountId");
        }
        if (PlayerCreation.Status.OK.equals(playerCreation.getStatus())) {
            playerRepository.save(player);
        }
        return playerCreation;
    }

    protected void checkNullValue(PlayerCreation playerCreation, String value, String fieldName) {
        if (value == null) {
            playerCreation.setStatus(PlayerCreation.Status.KO);
            playerCreation.getFields().put(fieldName, PlayerCreation.FieldStatus.cantBeNull);
        }
    }

    protected void alreadyExists(PlayerCreation playerCreation, String fieldName) {
        playerCreation.setStatus(PlayerCreation.Status.KO);
        playerCreation.getFields().put(fieldName, PlayerCreation.FieldStatus.alreadyExits);
    }
}
