package com.auguryrock.luv4s.service.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by MonCherWatson on 19/06/2015.
 */
public class PreJwtAuthentication extends AbstractAuthenticationToken {
    private Object principal;


    public PreJwtAuthentication(String token, RequestDetails rqDetails) {
        super(null);
        principal = token;
        setDetails(rqDetails);
    }

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the
     *                    principal represented by this authentication object.
     */
    public PreJwtAuthentication(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    public RequestDetails getRequestDetails() {
        return (RequestDetails) getDetails();
    }
    public String getJwt() {
        return (String) getPrincipal();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}