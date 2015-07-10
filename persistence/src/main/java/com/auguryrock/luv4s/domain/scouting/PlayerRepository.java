package com.auguryrock.luv4s.domain.scouting;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

/**
 * Created by MonCherWatson on 12/06/2015.
 */
public interface PlayerRepository extends Repository<Player, Integer>, CrudRepository<Player, Integer> {
    Player findByName(String name);

    Player findByAccountId(String accountId);

    Player findByNameAndPassword(String name, String password);

}
