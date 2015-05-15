package com.auguryrock.luv4s;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"/applicationContext-rest.xml", "/applicationContext-derby.xml"})
public class Luv4sApplication {

    public static void main(String[] args) {
        SpringApplication.run(Luv4sApplication.class);
    }
}
