package com.auguryrock.luv4s.domain;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Jose on 15/05/2015.
 */
@Configuration
@ComponentScan(basePackages = {"com.auguryrock.luv4s.domain"})
@ImportResource("/applicationContext-derby.xml")
public class TestContextConfiguration {
}
