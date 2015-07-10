package com.auguryrock.luv4s.service.security;

import com.auguryrock.luv4s.domain.scouting.Player;
import com.auguryrock.luv4s.service.player.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by MonCherWatson on 19/06/2015.
 */
@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {
    private final static Logger logger = LoggerFactory.getLogger(TokenAuthenticationProvider.class);
    @Autowired
    private SecurityService securityService;
    @Autowired
    private PlayerService playerService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            logger.info("Processing PreJwtAuthentication...");
            PreJwtAuthentication preJwtAuthentication = (PreJwtAuthentication) authentication;
            String token = preJwtAuthentication.getJwt();
            logger.info("Jwt Token: " + token);
            String playerName = securityService.getPlayerNameFromToken(token);
            logger.info("playerName: " + playerName);
            Player player = playerService.findByName(playerName);
            logger.info("player: " + player.getPk());
            Set<GrantedAuthority> grantedAuthorities = securityService.getAuthorities(player, preJwtAuthentication.getRequestDetails().getScoutingKey());
            logger.info("grantedAuthorities: " + grantedAuthorities);
            JwtAuthentication jwtAuthentication = new JwtAuthentication(player, grantedAuthorities);
            jwtAuthentication.setDetails(preJwtAuthentication.getDetails());
            logger.info("jwtAuthentication:" + jwtAuthentication);
            jwtAuthentication.setAuthenticated(true);
            return jwtAuthentication;
        } catch (RuntimeException e) {
            throw new BadCredentialsException(e.getMessage(), e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        logger.info("Supports method call: " + authentication);

        return authentication.equals(PreJwtAuthentication.class);
    }
}
