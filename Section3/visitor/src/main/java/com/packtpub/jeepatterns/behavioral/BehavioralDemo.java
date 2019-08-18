package com.packtpub.jeepatterns.behavioral;

import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import com.packtpub.jeepatterns.behavioral.visitor.IndentedFileVisitor;
import com.packtpub.jeepatterns.behavioral.visitor.VerySimpleFileVisitor;

/**
 * Jakarta EE Design Patterns Behavioral Patterns
 *
 * @author Werner Keil
 */
public class BehavioralDemo {

	public static void main(String[] args) throws Exception {
		if (args != null && args.length > 0) {
			new BehavioralDemo().start(args[0]);
		} else {
			new BehavioralDemo().start("");
		}
	}

	private void start(String option) throws Exception {
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

	private boolean doOption(String option) throws Exception {
		switch (option) {
		case "q":
		case "Q":
			return true;
		case "5":
			visitorPatternDemo();
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
		System.out.println("5. Visitor Pattern");

		System.out.println();
		System.out.println("---------------------------------------------------------------------------");
	}

	/**
	 * Visitor Pattern demo.
	 */
	private void visitorPatternDemo() throws Exception {
		// Set the starting path
		Path startingPath = Paths.get(".");

		// Instantiate the Visitor object
		FileVisitor<Path> visitor;
		visitor = new VerySimpleFileVisitor();

		// Use the built-in walkFileTree client to visit all directory,file nodes
		Files.walkFileTree(startingPath, visitor);
		
		System.out.println();
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("Now the same with indention");
		System.out.println();
		visitor = new IndentedFileVisitor();

		// visit all directory,file nodes again with indention
		Files.walkFileTree(startingPath, visitor);
	}
}