package com.packtpub.jeepatterns.singleton;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * A Singleton REST service which is able to return a unique ID.
 */

@Path("id")
public class SingletonIdEndpoint {
    @Inject
    SingletonIdService service;

    @GET
    public String get() {
      return "Id is " + service.getId();
    }
}
