package com.auguryrock.luv4s.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface MatchupRepository extends CrudRepository<Matchup, String>, Repository<Matchup, String> {
    List<Matchup> findByZone(Zone zone);
}
