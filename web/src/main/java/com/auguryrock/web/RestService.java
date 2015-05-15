package com.auguryrock.web;

import com.auguryrock.luv4s.domain.Matchup;
import com.auguryrock.luv4s.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestService {
    @Autowired
    private MatchService matchService;


    @Path("/matches")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Matchup> getCurrentMatches() {
        List<Matchup> currentMatches = matchService.getCurrentMatches();
        return currentMatches;
    }

    public void setMatchService(MatchService matchService) {
        this.matchService = matchService;
    }
}
