package com.auguryrock.luv4s.web;

import com.auguryrock.luv4s.domain.*;
import com.auguryrock.luv4s.domain.scouting.Player;
import com.auguryrock.luv4s.domain.scouting.ScoutingSession;
import com.auguryrock.luv4s.service.MatchupService;
import com.auguryrock.luv4s.service.TranslationService;
import com.auguryrock.luv4s.service.player.PlayerCreation;
import com.auguryrock.luv4s.service.player.PlayerService;
import com.auguryrock.luv4s.service.security.SecurityService;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
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

    @Path("/player/login/{player}/{password}")
    @GET
    public Response getToken(@PathParam("player") String user, @PathParam("password") String password) {
        try {
            String jwt = securityService.checkCredentialsAndGenerateJwt(user, password);
            return Response.status(Response.Status.OK).entity(jwt).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }


    @Path("/player/create")
    @POST
    public Response createAccount(Player player) {
        logger.info("Create player: "+player.getName());
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

    @Path("/scoutingsessions")
    @GET
    public List<ScoutingSession> getScoutingSessions() {
        logger.info(SecurityContextHolder.getContext().getAuthentication().getName());
        logger.info(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        return Collections.EMPTY_LIST;
    }

    public void setMatchupService(MatchupService matchupService) {
        this.matchupService = matchupService;
    }

    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }
}
