package com.auguryrock.luv4s.web;

import com.auguryrock.luv4s.domain.Language;
import com.auguryrock.luv4s.domain.Matchup;
import com.auguryrock.luv4s.domain.Translation;
import com.auguryrock.luv4s.domain.Zone;
import com.auguryrock.luv4s.domain.scouting.Player;
import com.auguryrock.luv4s.domain.scouting.ScoutingKey;
import com.auguryrock.luv4s.domain.scouting.ScoutingSession;
import com.auguryrock.luv4s.service.MatchupService;
import com.auguryrock.luv4s.service.ScoutingService;
import com.auguryrock.luv4s.service.TranslationService;
import com.auguryrock.luv4s.service.player.PlayerCreation;
import com.auguryrock.luv4s.service.player.PlayerService;
import com.auguryrock.luv4s.service.security.SecurityService;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@CrossOriginResourceSharing(allowAllOrigins = true)
@Component
public class RestService {
    private final static Logger logger = LoggerFactory.getLogger(RestService.class);

    @Autowired
    private MatchupService matchupService;
    @Autowired
    private TranslationService translationService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private ScoutingService scoutingService;


    @Path("/matches")
    @GET
    public List<Matchup> getCurrentMatchesByZone(@QueryParam("zone") Zone zone) {
        if (zone != null) {
            return matchupService.getCurrentMatchesByZone(zone);
        } else {
            return matchupService.getCurrentMatches();
        }
    }

    @Path("/matches/{matchId}")
    @GET
    public Matchup getMatch(@PathParam("matchId") String id) {
        return matchupService.getMatch(id);
    }

    @Path("/login/{player}/{password}")
    @GET
    public Response getToken(@PathParam("player") String user, @PathParam("password") String password) {
        try {
            Player player = playerService.findByNameAndPassword(user, password);
            String jwt = securityService.checkCredentialsAndGenerateJwt(player);
            return Response.status(Response.Status.OK).entity(new TokenResponse(jwt)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }


    @Path("/players")
    @POST
    public Response createAccount(JsonPlayer jp) {
        logger.info("Create jp: " + jp.name);
        logger.info("password: " + jp.password);

        Player player = new Player(jp.name, jp.accountId, jp.password, jp.description);
        PlayerCreation playerCreation = playerService.createPlayer(player);
        if (PlayerCreation.Status.KO.equals(playerCreation.getStatus())) {
            return Response.status(Response.Status.CONFLICT).entity(playerCreation).build();
        }
        return Response.status(Response.Status.OK).entity(playerCreation).build();
    }


    @Path("/translations")
    @GET
    public List<Translation> getTranslationsByLanguage(@QueryParam("lang") Language language) {
        return translationService.getTranslationsByLanguage(language);
    }

    @Path("/keys")
    @POST
    public ScoutingKey createScoutingKey(JsonNewKey newKey) {
        return scoutingService.createScoutingKey(Integer.valueOf(newKey.wordId));
    }


    @Path("/scoutingsessions")
    @GET
    public List<ScoutingSession> findScoutingSessions(@QueryParam("key") String scoutingKey, @QueryParam("objectiveId") Integer objectiveId) {
        return scoutingService.findScoutingSessions(scoutingKey, objectiveId);
    }


    @Path("/scoutingsessions")
    @POST
    public ScoutingSession createScoutingSession(JsonScoutingSession ssc) {
        return scoutingService.createScoutingSession(ssc.start, ssc.end, ssc.description, ssc.key, ssc.objectiveId);
    }


    public void setMatchupService(MatchupService matchupService) {
        this.matchupService = matchupService;
    }

    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    protected static class TokenResponse {
        public String token;

        public TokenResponse(String token) {
            this.token = token;
        }
    }

    protected static class JsonScoutingSession {
        protected String key;
        private Integer pk;
        private Date start;
        private Date end;
        private String description;
        protected Integer objectiveId;
    }

    public static class JsonPlayer {
        public String name;
        public String accountId;
        public String password;
        public String description;
    }

    public static class JsonNewKey {
        public String wordId;
    }
}
