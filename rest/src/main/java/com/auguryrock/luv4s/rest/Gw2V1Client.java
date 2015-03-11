package com.auguryrock.luv4s.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

/**
 * Created by p127638 on 18.02.2015.
 */
@Path("/v1")
public interface Gw2V1Client {
    @Path("/wvw/matches.json")
    @GET
    WvWMatches getAllMatches();

    @Path("/wvw/match_details.json")
    @GET
    WvWMatchDetails getMatchDetails(@QueryParam("match_id") String matchId);


}
