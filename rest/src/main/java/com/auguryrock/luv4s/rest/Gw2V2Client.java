package com.auguryrock.luv4s.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * Created by p127638 on 11.03.2015.
 */
@Path("/v2")
public interface Gw2V2Client {
    @Path("/worlds")
    @GET
    public List<JsonWorld> getAllWorlds(@QueryParam("ids") String ids, @QueryParam("lang") String lang);

}
