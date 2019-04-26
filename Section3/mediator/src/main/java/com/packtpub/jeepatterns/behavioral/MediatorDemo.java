package com.packtpub.jeepatterns.behavioral;

import java.util.Scanner;

/**
 * Jakarta EE Design Patterns Behavioral Patterns
 *
 * @author Werner Keil
 */
public class MediatorDemo {

	public static void main(String[] args) {
		if (args != null && args.length > 0) {
			new MediatorDemo().start(args[0]);
		} else {
			new MediatorDemo().start("");
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
		case "2":
			mediatorPatternDemo();
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
		System.out.println("2. Mediator Pattern");

		System.out.println();
		System.out.println("---------------------------------------------------------------------------");
	}

	/**
	 * Mediator Pattern demo.
	 */
	private void mediatorPatternDemo() {
		// Builder factory
		{
		    final ChatMediator chatMediator = new ChatRoom();
		    User john = new ChatUser(chatMediator, "John");
		    User david = new ChatUser(chatMediator, "David");
		    User susan = new ChatUser(chatMediator, "Susan");
		    User moderator = new ChatUser(chatMediator, "Moderator");
		    
		    chatMediator.registerUser(john);
		    chatMediator.registerUser(david);
		    chatMediator.registerUser(susan);
		    chatMediator.registerUser(moderator);

		    moderator.send("Hello everybody. Welcome to our meeting.");
		    john.send("Hello Moderator.");
		  }
	}
}