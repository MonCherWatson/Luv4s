package com.auguryrock.luv4s.domain.scouting;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

/**
 * Created by MonCherWatson on 19/06/2015.
 */
public interface RoleRepository extends Repository<Role, Integer>, CrudRepository<Role, Integer> {

    @Query("select r from Role r where r.player = ?1 and r.scoutingKey.uuid = ?2")
    Role findByPlayerAndScoutingKey(Player player, String scoutingKey);
}
