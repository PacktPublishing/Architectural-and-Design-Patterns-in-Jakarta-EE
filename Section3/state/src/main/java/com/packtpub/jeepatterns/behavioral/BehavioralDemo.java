package com.packtpub.jeepatterns.behavioral;

import java.util.Scanner;

/**
 * Jakarta EE Design Patterns Behavioral Patterns
 *
 * @author Werner Keil
 */
public class BehavioralDemo {

	public static void main(String[] args) {
		if (args != null && args.length > 0) {
			new BehavioralDemo().start(args[0]);
		} else {
			new BehavioralDemo().start("");
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
		case "3":
			statePatternDemo();
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
		System.out.println();
		System.out.println("Choose a pattern to demonstrate or press 'Q' to exit:");
		System.out.println();
		System.out.println("3. State Pattern");

		System.out.println();
		System.out.println("---------------------------------------------------------------------------");
	}

	/**
	 * State Pattern demo.
	 */
	private void statePatternDemo() {
		// TV Remote States

		final Context context = new TVRemoteContext();
		State tvState1 = new TVOnState();
		State tvState2 = new TVStandbyState();
		State tvState3 = new TVOffState();

		context.setState(tvState1);
		context.request();

		context.setState(tvState2);
		context.request();
		
		context.setState(tvState3);
		context.request();
	}
}