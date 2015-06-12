package com.auguryrock.luv4s.web;

import com.auguryrock.luv4s.domain.Language;
import com.auguryrock.luv4s.domain.Matchup;
import com.auguryrock.luv4s.domain.Translation;
import com.auguryrock.luv4s.domain.Zone;
import com.auguryrock.luv4s.service.MatchupService;
import com.auguryrock.luv4s.service.TranslationService;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@CrossOriginResourceSharing(allowAllOrigins = true)
@Component
public class RestService {
    @Autowired
    private MatchupService matchupService;
    @Autowired
    private TranslationService translationService;


    @Path("/matches")
    @GET
    public List<Matchup> getCurrentMatchesByZone(@QueryParam("zone") Zone zone) {
        if (zone != null) {
            return matchupService.getCurrentMatchesByZone(zone);
        } else {
            return  matchupService.getCurrentMatches();
        }
    }

    @Path("/matches/{matchId}")
    @GET
    public Matchup getMatch(@PathParam("matchId") String id) {
        return matchupService.getMatch(id);
    }


    @Path("/translations")
    @GET
    public List<Translation> getTranslationsByLanguage(@QueryParam("lang") Language language) {
        return translationService.getTranslationsByLanguage(language);
    }

    public void setMatchupService(MatchupService matchupService) {
        this.matchupService = matchupService;
    }
}
