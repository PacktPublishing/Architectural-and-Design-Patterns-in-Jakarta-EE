package com.packtpub.jeepatterns.behavioral.strategy;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class StrategyProducer {
	private static final String STRATEGY_PREFIX = "com.packtpub.jeepatterns.behavioral.strategy.Strategy";
	
	@Produces
	public Strategy getInstance(@Any @Implementation Instance<Strategy> instances, InjectionPoint injectionPoint) {
		final String strategy = STRATEGY_PREFIX + System.getProperty("strategy.number");
		
		@SuppressWarnings("rawtypes")
		Map<Class, Strategy> strategies = new HashMap<>();
		for (Strategy a : instances) {
			strategies.put(a.getClass(), a);
		}

		try {
			return strategies.get(Class.forName(strategy));
			
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Unknown strategy");
		}
	}
}
