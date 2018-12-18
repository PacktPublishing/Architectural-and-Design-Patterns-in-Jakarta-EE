package com.packtpub.jeepatterns.singleton;

import java.util.UUID;

import javax.inject.Singleton;

/**
 * A simple CDI service which is able to return an id
 *
 */
@Singleton
public class SingletonIdService {
	private UUID id = UUID.randomUUID();

	public UUID getId() {
		return id;
	}

}
