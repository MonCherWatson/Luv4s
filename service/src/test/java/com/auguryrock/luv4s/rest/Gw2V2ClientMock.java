package com.auguryrock.luv4s.rest;

import com.fasterxml.jackson.core.type.TypeReference;

import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * Created by MonCherWatson on 01/05/2015.
 */
public class Gw2V2ClientMock extends AbstractMockRestClient implements Gw2V2Client {
    @Override
    public List<JsonWorld> getWorlds(@QueryParam("ids") String ids, @QueryParam("lang") String lang) {
        return unmarshallWithTypeReference("test-worlds.json", new TypeReference<List<JsonWorld>>() {
        });
    }
}
