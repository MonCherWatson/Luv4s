package com.auguryrock.luv4s.rest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by MonCherWatson on 15/05/2015.
 */
@Configuration
@ComponentScan(basePackages = {"com.auguryrock.luv4s.rest"})
@ImportResource("/applicationContext-rest-client.xml")
public class TestContextConfiguration {
}
