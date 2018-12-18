package com.packtpub.jeepatterns.singleton;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * A simple REST service which is able to say hello to someone using HelloService Please take a look at the web.xml where JAX-RS
 * is enabled
 */

@Path("multiejb")
public class MultiThreadedEndpointEJB {
    @Inject
    MultiThreadSingletonEJB service;

    @GET
    public String start() {
      for (int i = 0; i < 10; i++) {
        new Thread(new Runnable() {
          @Override
          public void run() {
            service.testMethod();
          }
        }).start();
      }
      return "Hello";
    }
}
