package com.packtpub.jeepatterns.singleton;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

/**
 * A simple CDI service which is able to return an id
 *
 */
@ApplicationScoped
public class ApplicationScopedService {
	private UUID id = UUID.randomUUID();

	public UUID getId() {
		return id;
	}

}
