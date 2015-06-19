package com.auguryrock.luv4s.service.security;

import com.auguryrock.luv4s.service.player.PlayerService;
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
    @Autowired
    private SecurityService securityService;
    @Autowired
    private PlayerService playerService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            PreJwtAuthentication preJwtAuthentication = (PreJwtAuthentication) authentication;
            String token = preJwtAuthentication.getJwt();
            String username = securityService.getPlayerNameFromToken(token);
            Set<GrantedAuthority> grantedAuthorities = securityService.getAuthorities(username, preJwtAuthentication.getRequestDetails().getScoutingKey());

            JwtAuthentication jwtAuthentication = new JwtAuthentication(username, grantedAuthorities);
            jwtAuthentication.setDetails(preJwtAuthentication.getDetails());

            return jwtAuthentication;
        } catch (RuntimeException e) {
            throw new BadCredentialsException(e.getMessage(), e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(PreJwtAuthentication.class);
    }
}
