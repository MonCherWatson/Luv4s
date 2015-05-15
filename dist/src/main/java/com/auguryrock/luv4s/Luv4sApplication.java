package com.auguryrock.luv4s;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.Filter;

@SpringBootApplication
@ImportResource({"/applicationContext-rest-client.xml", "/applicationContext-derby.xml"
        , "/applicationContext-rest-service.xml"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="com.auguryrock.luv4s.domain")
@EnableScheduling
public class Luv4sApplication {

    @Bean
    public ServletRegistrationBean cxfServlet() {
        return new ServletRegistrationBean(new CXFServlet(), "/ws/*");
    }

    @Bean
    public Filter compressingFilter() {
        return new OpenEntityManagerInViewFilter();
    }
    public static void main(String[] args) {
        SpringApplication.run(Luv4sApplication.class);
    }
}
