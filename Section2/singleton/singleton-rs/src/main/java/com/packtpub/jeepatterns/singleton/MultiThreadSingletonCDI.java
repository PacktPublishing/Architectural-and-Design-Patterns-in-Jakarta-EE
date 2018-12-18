package com.packtpub.jeepatterns.singleton;

import javax.inject.Singleton;
import javax.ejb.Startup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Startup
@Singleton
public class MultiThreadSingletonCDI {

	private static final Logger log = LoggerFactory.getLogger(MultiThreadSingletonCDI.class);
	private int counter = 0;

	public void testMethod() {
		counter++;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		if (counter == 1) {
			log.info("Everything is okay.");
		} else {
			log.info("Counter is " + counter);
		}
		counter--;
	}
}