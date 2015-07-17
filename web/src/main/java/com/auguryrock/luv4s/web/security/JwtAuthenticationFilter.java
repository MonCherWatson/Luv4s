package com.auguryrock.luv4s.web.security;

import com.auguryrock.luv4s.service.security.PreJwtAuthentication;
import com.auguryrock.luv4s.service.security.RequestDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by MonCherWatson on 12/06/2015.
 */
@Component
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final static Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Resource(name = "customAuthenticationManager")
    private AuthenticationManager authenticationManager;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("Checking for Jwt token...");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String token = httpServletRequest.getHeader("X-Auth-Token");

        try {
            if (token != null) {
                String ipAddress = httpServletRequest.getRemoteAddr();
                String scoutingKey = httpServletRequest.getParameter("scoutingKey");
                RequestDetails requestDetails = new RequestDetails(ipAddress, scoutingKey);
                PreJwtAuthentication preJwtAuthentication = new PreJwtAuthentication(token, requestDetails);
                Authentication result = authenticationManager.authenticate(preJwtAuthentication);
                logger.info("result:" + result);
                if (result == null || !result.isAuthenticated()) {
                    throw new InternalAuthenticationServiceException("Unable to authenticate Domain Player for provided credentials");
                }
                SecurityContextHolder.getContext().setAuthentication(result);
            } else {
                logger.info("No Jwt token");
            }
            chain.doFilter(request, response);
        } catch (AuthenticationException e) {
            logger.error(e.getMessage(), e);
            SecurityContextHolder.clearContext();
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }


    }
}
