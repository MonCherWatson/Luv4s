package com.auguryrock.luv4s.service;

import com.auguryrock.luv4s.domain.Matchup;
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
    protected MatchService matchService;


    @Path("/matches")
    @GET
    public List<Matchup> getCurrentMatches() {
        return matchService.getCurrentMatches();
    }
}
