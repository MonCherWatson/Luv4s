package com.auguryrock.luv4s.web;

import com.auguryrock.luv4s.domain.Matchup;
import com.auguryrock.luv4s.domain.Zone;
import com.auguryrock.luv4s.service.MatchupService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Jose on 09/05/2015.
 */
@RunWith(JMockit.class)
public class RestServiceTest extends JAXRSTest<RestService> {
    private RestService restService = new RestService();
    @Mocked
    private MatchupService matchupService;

    @Before
    public void init() {
        restService.setMatchupService(matchupService);
        initWebServices();
    }

    @Test
    public void test_matches() throws JsonProcessingException {
        new Expectations() {{
            matchupService.getCurrentMatches();
            result = new Matchup();
        }};

        Client client = ClientBuilder.newBuilder().newClient().register(JacksonJsonProvider.class);
        WebTarget target = client.target("http://localhost:8080/luv4s");
        List<Matchup> matches = target.path("matches").request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<Matchup>>() {
        });
        assertThat(matches).hasSize(1);

        new Verifications() {{
            matchupService.getCurrentMatches();
            times = 1;
        }};

    }

    @Test
    public void test_matches_by_zone() throws JsonProcessingException {
        new Expectations() {{
            matchupService.getCurrentMatchesByZone(Zone.EU);
            result = new Matchup();
        }};

        Client client = ClientBuilder.newBuilder().newClient().register(JacksonJsonProvider.class);
        WebTarget target = client.target("http://localhost:8080/luv4s");
        List<Matchup> matches = target.path("matches/EU").request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<Matchup>>() {
        });
        assertThat(matches).hasSize(1);

        new Verifications() {{
            matchupService.getCurrentMatchesByZone(Zone.EU);
            times = 1;
        }};

    }



    @Override
    protected RestService getResourceInstance() {
        return restService;
    }

    @Override
    protected Class<RestService> getResourceClass() {
        return RestService.class;
    }
}
