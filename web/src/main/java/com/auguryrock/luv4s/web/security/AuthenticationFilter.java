package com.auguryrock.luv4s.web.security;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by MonCherWatson on 12/06/2015.
 */
public class AuthenticationFilter  extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Object token = request.getAttribute("token");
        if ("user*password".equals(token)) {

        }

        chain.doFilter(request, response);
    }
}
