package com.packtpub.jeepatterns.singleton;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * A REST API which returns a unique ID using an Application scoped bean.
 */

@Path("id")
public class ApplicationScopedEndpoint {
    @Inject
    ApplicationScopedService service;

    @GET
    public String get() {
      return "Id is " + service.getId();
    }
}
