package com.auguryrock.luv4s.rest;

import com.auguryrock.luv4s.rest.Gw2V1Client;
import com.auguryrock.luv4s.rest.JsonMatchDetails;
import com.auguryrock.luv4s.rest.JsonMatches;

import javax.ws.rs.QueryParam;

/**
 * Created by MonCherWatson on 01/05/2015.
 */
public class Gw2V1ClientMock extends AbstractMockRestClient implements Gw2V1Client {
    @Override
    public JsonMatches getAllMatches() {
        return unmarshall("/test-matches.json", JsonMatches.class);
    }

    @Override
    public JsonMatchDetails getMatchDetails(@QueryParam("match_id") String matchId) {
        return unmarshall("/test-match_details.json", JsonMatchDetails.class);
    }
}
