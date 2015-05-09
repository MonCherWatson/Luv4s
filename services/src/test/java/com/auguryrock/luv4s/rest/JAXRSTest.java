package com.auguryrock.luv4s.rest;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jose on 09/05/2015.
 */
public abstract class JAXRSTest<T> {
    private final static String ENDPOINT_ADDRESS = "http://localhost:8080/luv4s";
    private Server server;

    @BeforeClass
    public void initialize() throws Exception {
        startServer();
    }


    private void startServer() throws Exception {
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        sf.setResourceClasses(getResourceClass());

        List<Object> providers = new ArrayList<>();
        // add custom providers if any
        sf.setProviders(providers);

        sf.setResourceProvider(getResourceClass(),
        new SingletonResourceProvider(getResourceInstance(), true));
        sf.setAddress(ENDPOINT_ADDRESS);

        server = sf.create();
    }

    @AfterClass
    public void destroy() throws Exception {
        server.stop();
        server.destroy();
    }

    abstract protected T getResourceInstance();

    abstract protected Class<T> getResourceClass();


}
