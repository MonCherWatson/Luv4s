package com.auguryrock.luv4s.domain;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by MonCherWatson on 15/05/2015.
 */
@Configuration
@ComponentScan(basePackages = {"com.auguryrock.luv4s.domain"})
@ImportResource("/applicationContext-derby.xml")
@EnableJpaRepositories(basePackages = "com.auguryrock.luv4s.domain")
public class TestContextConfiguration {
}
