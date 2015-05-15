package com.auguryrock.luv4s.service;

import com.auguryrock.luv4s.rest.Gw2V1Client;
import com.auguryrock.luv4s.rest.Gw2V1ClientMock;
import com.auguryrock.luv4s.rest.Gw2V2Client;
import com.auguryrock.luv4s.rest.Gw2V2ClientMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Jose on 15/05/2015.
 */
@Configuration
@ComponentScan(basePackages = {"com.auguryrock.luv4s"})
@ImportResource("/applicationContext-derby.xml")
public class TestContextConfiguration {
    @Bean
    Gw2V1Client gw2V1Client() {
        return new Gw2V1ClientMock();
    }

    @Bean
    Gw2V2Client gw2V2Client() {
        return new Gw2V2ClientMock();
    }
}

