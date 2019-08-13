package com.packtpub.jeepatterns.behavioral.strategy;

import java.util.Scanner;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

/**
 * Jakarta EE Design Patterns Behavioral Patterns
 *
 * @author Werner Keil
 */
public class StrategyDemo {

	public static void main(String[] args) {
		if (args != null && args.length > 0) {
			new StrategyDemo().start(args[0]);
		} else {
			new StrategyDemo().start("");
		}
	}

	private void start(String option) {
		if (option != null && !option.isEmpty()) {
			doOption(option);
		} else {
			try (Scanner scanner = new Scanner(System.in)) {
				while (true) {
					printOptions();
					boolean done = doOption(scanner.nextLine());
					if (done)
						return;
				}
			}
		}
	}

	private boolean doOption(String option) {
		switch (option) {
		case "q":
		case "Q":
			return true;
		case "1":
		case "2":
			strategyDemo(option);
			return false;
		default:
			return false;
		}
	}

	private void printOptions() {
		System.out.println();
		System.out.println("----------------------- Jakarta EE Design Patterns -----------------------");
		System.out.println();
		System.out.println("Section 3 - Behavioral Patterns");
		System.out.println("4. Strategy Pattern");	
		System.out.println();
		System.out.println("Choose a strategy or press 'Q' to exit:");
		System.out.println();
		System.out.println("1. Strategy 1");	
		System.out.println("2. Strategy 2");

		System.out.println();
		System.out.println("---------------------------------------------------------------------------");
	}

	/**
	 * Strategy Pattern demo.
	 */
	private void strategyDemo(String strategy) {
		System.setProperty("strategy.number", strategy);
		
		try (SeContainer cdiContainer = SeContainerInitializer.newInstance().initialize()) {
			System.out.println("Getting context...");
			Context c = cdiContainer.select(Context.class).get();
			c.operation();
		}
	}
}