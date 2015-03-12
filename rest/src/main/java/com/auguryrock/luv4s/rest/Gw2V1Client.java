package com.auguryrock.luv4s.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

@Path("/v1")
public interface Gw2V1Client {
    @Path("/wvw/matches.json")
    @GET
    JsonMatches getAllMatches();

    @Path("/wvw/match_details.json")
    @GET
    JsonMatchDetails getMatchDetails(@QueryParam("match_id") String matchId);


}
