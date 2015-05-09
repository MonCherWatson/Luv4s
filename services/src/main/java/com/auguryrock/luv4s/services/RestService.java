package com.auguryrock.luv4s.services;

import com.auguryrock.luv4s.persistence.Match;
import com.auguryrock.luv4s.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/")
public class RestService {
    @Autowired
    private MatchService matchService;


    @Path("/matches")
    @GET
    public List<Match> getCurrentMatches() {
        return matchService.getCurrentMatches();
    }
}
