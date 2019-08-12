package com.packtpub.jeepatterns.behavioral.strategy;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

public class Context {

	@Inject
	private Instance<Strategy> instance;

	public void operation() {
		Strategy strategy = instance.get();
		strategy.execute();
	}
}
