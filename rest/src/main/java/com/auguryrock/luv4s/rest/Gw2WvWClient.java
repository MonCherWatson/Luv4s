package com.auguryrock.luv4s.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

/**
 * Created by p127638 on 18.02.2015.
 */
@Path("/wvw")
public interface Gw2WvWClient {
    @Path("/matches.json")
    @GET
    WvWMatches getAllMatches();

    @Path("/match_details.json")
    @GET
    WvWMatchDetails getMatchDetails(@QueryParam("match_id") String matchId);


}
