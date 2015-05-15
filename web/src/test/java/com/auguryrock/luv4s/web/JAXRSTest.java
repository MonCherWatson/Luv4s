package com.auguryrock.luv4s.web;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.junit.After;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jose on 09/05/2015.
 */
public abstract class JAXRSTest<T> {
    protected final static String ENDPOINT_ADDRESS = "http://localhost:8080/luv4s";
    private Server server;

    public void initWebServices() {
        startServer();
    }


    private void startServer() {
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        sf.setResourceClasses(getResourceClass());

        List<Object> providers = new ArrayList<>();
        // add custom providers if any
        providers.add(new JacksonJsonProvider());
        sf.setProviders(providers);

        sf.setResourceProvider(getResourceClass(),
                new SingletonResourceProvider(getResourceInstance(), true));
        sf.setAddress(ENDPOINT_ADDRESS);

        server = sf.create();
    }

    @After
    public void destroy() throws Exception {
        server.stop();
        server.destroy();
    }

    abstract protected T getResourceInstance();

    abstract protected Class<T> getResourceClass();


}
