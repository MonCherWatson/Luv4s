package com.auguryrock.luv4s;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {"com.auguryrock.luv4s"})
@ImportResource({"/applicationContext-rest.xml", "/applicationContext-derby.xml"})
public class Luv4sApplication {

}
