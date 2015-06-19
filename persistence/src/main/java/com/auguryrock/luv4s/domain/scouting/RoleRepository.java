package com.auguryrock.luv4s.domain.scouting;

import com.auguryrock.luv4s.domain.Translation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by MonCherWatson on 19/06/2015.
 */
public interface RoleRepository extends Repository<Role, Integer>, CrudRepository<Role, Integer> {

    @Query("select r from Role r where r.player.name = ?1 and r.scoutingKey.token = ?2")
    public Role findByUserAndScoutingKey(String username, String scoutingKey);
}
