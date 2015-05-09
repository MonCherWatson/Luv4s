package com.auguryrock.luv4s.services;

import com.auguryrock.luv4s.rest.JAXRSTest;
import org.junit.Before;

/**
 * Created by Jose on 09/05/2015.
 */
public class RestServiceTest extends JAXRSTest<RestService> {
    private RestService restService;

    @Before
    public void init() {

    }

    @Override
    protected String getEndpointAddress() {
        return "//local://luv4s";
    }

    @Override
    protected RestService getResourceInstance() {
        return restService;
    }

    @Override
    protected Class<RestService> getResourceClass() {
        return RestService.class;
    }
}
