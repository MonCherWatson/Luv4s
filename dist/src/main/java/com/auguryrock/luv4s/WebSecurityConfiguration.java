package com.auguryrock.luv4s;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by MonCherWatson on 12/06/2015.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/api/matches/**").antMatchers("/api/translations").antMatchers("/api/login");
    }
}
