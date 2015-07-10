package com.auguryrock.luv4s.domain.scouting;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by MonCherWatson on 10/07/2015.
 */
public interface ScoutingSessionRepository extends Repository<ScoutingSession, Integer>, CrudRepository<ScoutingSession, Integer> {
    @Query("select s from ScoutingSession s where s.objective.pk = ?1 and s.scoutingKey.uuid = ?2")
    List<ScoutingSession> findByObjectiveAndScoutingKeyAnd(Integer objectiveId, String scoutingKeyUid);
}
