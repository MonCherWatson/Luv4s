package com.auguryrock.luv4s.service;

import com.auguryrock.luv4s.domain.Matchup;
import com.auguryrock.luv4s.rest.JAXRSTest;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Jose on 09/05/2015.
 */
@RunWith(JMockit.class)
public class RestServiceTest extends JAXRSTest<RestService> {
    private RestService restService = new RestService();
    @Mocked
    private MatchService matchService;

    @Before
    public void init() {
        restService.matchService = matchService;
        initWebServices();
    }

    @Test
    public void test() {
        new Expectations() {{
            matchService.getCurrentMatches();
            result = new Matchup();
        }};

        List<Matchup> matchups = restService.getCurrentMatches();
        assertThat(matchups).hasSize(1);

        new Verifications() {{
            matchService.getCurrentMatches();
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
