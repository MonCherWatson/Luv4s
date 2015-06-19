package com.auguryrock.luv4s.service.security;

import com.auguryrock.luv4s.domain.scouting.RoleType;
import com.auguryrock.luv4s.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;

/**
 * Created by MonCherWatson on 19/06/2015.
 */
@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private PlayerService playerService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            PreJwtAuthentication preJwtAuthentication = (PreJwtAuthentication) authentication;
            String token = preJwtAuthentication.getJwt();
            String username = tokenService.getPlayerNameFromToken(token);
            RoleType roleType = playerService.getRoleType(username, preJwtAuthentication.getRequestDetails().getScoutingKey());

            JwtAuthentication jwtAuthentication = new JwtAuthentication(username, asList(new SimpleGrantedAuthority(roleType.toString())));
            jwtAuthentication.setDetails(preJwtAuthentication.getDetails());

            return jwtAuthentication;
        } catch(RuntimeException e) {
            throw new BadCredentialsException(e.getMessage(), e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(PreJwtAuthentication.class);
    }
}
