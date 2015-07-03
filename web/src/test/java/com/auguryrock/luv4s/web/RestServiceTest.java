package com.auguryrock.luv4s.web;

import com.auguryrock.luv4s.domain.Matchup;
import com.auguryrock.luv4s.domain.Zone;
import com.auguryrock.luv4s.domain.scouting.Player;
import com.auguryrock.luv4s.service.MatchupService;
import com.auguryrock.luv4s.service.player.PlayerCreation;
import com.auguryrock.luv4s.service.player.PlayerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by MonCherWatson on 09/05/2015.
 */
@RunWith(JMockit.class)
public class RestServiceTest extends JAXRSTest<RestService> {
    private RestService restService = new RestService();
    @Mocked
    private MatchupService matchupService;
    @Mocked
    private PlayerService playerService;

    @Before
    public void init() {
        restService.setMatchupService(matchupService);
        restService.setPlayerService(playerService);
        initWebServices();
    }

    @Test
    public void test_matches() throws JsonProcessingException {
        new Expectations() {{
            matchupService.getCurrentMatches();
            result = new Matchup();
        }};

        List<Matchup> matches = getWebTarget().path("matches").request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<Matchup>>() {
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

        List<Matchup> matches = getWebTarget().path("matches").queryParam("zone", Zone.EU).request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<Matchup>>() {
        });
        assertThat(matches).hasSize(1);

        new Verifications() {{
            matchupService.getCurrentMatchesByZone(Zone.EU);
            times = 1;
        }};

    }

    @Test
    public void test_match_by_id() throws JsonProcessingException {
        new Expectations() {{
            matchupService.getMatch("2-1");
            result = new Matchup();
        }};

        Matchup match = getWebTarget().path("matches/2-1").request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).get(Matchup.class);
        assertThat(match).isNotNull();
        new Verifications() {{
            matchupService.getMatch("2-1");
            times = 1;
        }};

    }

    @Test
    public void test_create_player() {
        Player player = new Player();
        player.setName("name");
        player.setAccountId("accountId");
        new Expectations() {{
            playerService.createPlayer(withInstanceOf(Player.class));
            result = new PlayerCreation();
        }};

        PlayerCreation creation = getWebTarget().path("player").request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(player), PlayerCreation.class);

        assertThat(creation).isNotNull();

        new Verifications() {{
            Player capPlayer;
            playerService.createPlayer(capPlayer = withCapture());
            times = 1;

            assertThat(capPlayer.getName().equals(player.getName()));
            assertThat(capPlayer.getAccountId().equals(player.getAccountId()));
        }};

    }


    @Test
    public void test_create_player_ko() {
        Player player = new Player();
        player.setName("name");
        player.setAccountId("accountId");

        final PlayerCreation playerCreation = new PlayerCreation();
        playerCreation.setStatus(PlayerCreation.Status.KO);
        new Expectations() {{
            playerService.createPlayer(withInstanceOf(Player.class));
            result = playerCreation;
        }};
        try {
            getWebTarget().path("player").request(MediaType.APPLICATION_JSON_TYPE)
                    .accept(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.json(player), PlayerCreation.class);
        } catch (ClientErrorException e) {
            Response response = e.getResponse();
            assertThat(response.getStatus()).isEqualTo(Response.Status.CONFLICT.getStatusCode());
            PlayerCreation pc = response.readEntity(PlayerCreation.class);
            assertThat(pc.getStatus().equals(PlayerCreation.Status.KO));
        }
        new Verifications() {{
            Player capPlayer;
            playerService.createPlayer(capPlayer = withCapture());
            times = 1;

            assertThat(capPlayer.getName().equals(player.getName()));
            assertThat(capPlayer.getAccountId().equals(player.getAccountId()));
        }};

    }

    protected WebTarget getWebTarget() {
        Client client = ClientBuilder.newBuilder().newClient().register(JacksonJsonProvider.class);
        return client.target("http://localhost:8080/luv4s");
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
