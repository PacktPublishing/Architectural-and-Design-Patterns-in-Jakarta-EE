package com.packtpub.jeepatterns.creational;

import static com.packtpub.jeepatterns.creational.ACL.Group.*;

import java.util.Scanner;

/**
 * Jakarta EE Design Patterns Creational Patterns Demo
 *
 * @author Werner Keil
 */
public class PrototypeDemo {

	public static void main(String[] args) {
		if (args != null && args.length > 0) {
			new PrototypeDemo().start(args[0]);
		} else {
			new PrototypeDemo().start("");
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
		case "4":
			prototypePatternDemo();
			return false;
		default:
			return false;
		}
	}

	private void printOptions() {
		System.out.println();
		System.out.println("----------------------- Jakarta EE Design Patterns -----------------------");
		System.out.println();
		System.out.println("Section 2 - Creational Patterns");
		System.out.println();
		System.out.println("Choose a pattern to demonstrate or press 'Q' to exit:");
		System.out.println();
		System.out.println("4. Prototype Pattern");

		System.out.println();
		System.out.println("---------------------------------------------------------------------------");
	}

	/**
	 * Prototype Pattern demo.
	 */
	private void prototypePatternDemo() {
		ACL userACL = AccessManagement.getAccessControlList(USER);
		User user = new User("Dilbert", "USER Role", userACL);
		
		System.out.println("---------------------------------------------------------------------------");
		System.out.println(user);
		
		userACL = AccessManagement.getAccessControlList(USER);
		user = new User("Wally", "USER Role", userACL);
		System.out.println(user);
		System.out.println("Changing permission of: "+ user.getName());
		user.getACL().setPermission("READ REPORTS");
		System.out.println(user);
				
		final ACL actACL = AccessManagement.getAccessControlList(ACCOUNTANT);
		user = new User("Bradley", "ACCOUNTANT Troll", actACL);
		System.out.println(user);
		
		final ACL managerACL = AccessManagement.getAccessControlList(MANAGER);
		user = new User("The Boss", "MANAGER Role", managerACL);
		System.out.println(user);
		
		System.out.println("---------------------------------------------------------------------------");
	}
}