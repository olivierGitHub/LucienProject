package com.rest;

import com.services.InitServices;
import com.services.PumpServices;
import com.test.ServiceTest;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by olivier on 20/11/15.
 */
public class MyServices extends Application {
    private Set<Object> singletons = new HashSet<Object>();

    public MyServices() {
        singletons.add(new ServiceTest());
        singletons.add(new InitServices());
        singletons.add(new PumpServices());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}

