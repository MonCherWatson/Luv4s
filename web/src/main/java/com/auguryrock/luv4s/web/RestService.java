package com.auguryrock.luv4s.web;

import com.auguryrock.luv4s.domain.Matchup;
import com.auguryrock.luv4s.service.MatchupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Component
public class RestService {
    @Autowired
    private MatchupService matchupService;


    @Path("/matches")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Matchup> getCurrentMatches() {
        List<Matchup> currentMatches = matchupService.getCurrentMatches();
        return currentMatches;
    }

    public void setMatchupService(MatchupService matchupService) {
        this.matchupService = matchupService;
    }
}
