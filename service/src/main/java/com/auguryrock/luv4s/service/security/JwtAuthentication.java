package com.auguryrock.luv4s.service.security;

import com.auguryrock.luv4s.domain.scouting.Player;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by MonCherWatson on 19/06/2015.
 */
public class JwtAuthentication extends AbstractAuthenticationToken {
    private Player player;


    public JwtAuthentication(Player player, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.player = player;
    }

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the
     *                    principal represented by this authentication object.
     */
    public JwtAuthentication(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return player;
    }
}
