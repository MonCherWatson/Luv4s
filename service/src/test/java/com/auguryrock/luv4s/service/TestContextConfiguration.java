package com.auguryrock.luv4s.service;

import com.auguryrock.luv4s.rest.Gw2V1Client;
import com.auguryrock.luv4s.rest.Gw2V1ClientMock;
import com.auguryrock.luv4s.rest.Gw2V2Client;
import com.auguryrock.luv4s.rest.Gw2V2ClientMock;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Jose on 15/05/2015.
 */
@Configuration
@ComponentScan(basePackages = {"com.auguryrock.luv4s"}, excludeFilters=@ComponentScan.Filter(type = FilterType.ANNOTATION, value=Configuration.class))
@ImportResource("/applicationContext-derby.xml")
@EnableJpaRepositories(basePackages="com.auguryrock.luv4s.domain")
public class TestContextConfiguration {
    @Bean
    public Gw2V1Client gw2V1Client() {
        return new Gw2V1ClientMock();
    }

    @Bean
    public Gw2V2Client gw2V2Client() {
        return new Gw2V2ClientMock();
    }
}

