package com.packtpub.jeepatterns.builder;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.json.stream.JsonGenerator;

/**
 * Jakarta EE Design Patterns
 *
 * @author Werner Keil
 */
public class Main {

	public static void main(String[] args) {
		if (args != null && args.length > 0) {
			new Main().start(args[0]);
		} else {
			new Main().start("");
		}
	}

	@SuppressWarnings("resource")
	private void start(String option) {
		if (option != null && !option.isEmpty()) {
			doOption(option);
		} else {
			while (true) {
				printOptions();
				Scanner scanner = new Scanner(System.in);
				boolean done = doOption(scanner.nextLine());
				if (done) return;
			}
		}
	}

	private boolean doOption(String option) {
		switch (option) {
		case "q":
		case "Q":
			return true;
		case "1":
			builderPatternDemo();
			return false;
		default:
			return false;
		}
	}

	private void printOptions() {
		System.out.println();
		System.out.println("----------------------- Jakarta EE Design Patterns -----------------------");
		System.out.println();
		System.out.println("Choose a pattern to demonstrate or press 'Q' to exit:");
		System.out.println();
		System.out.println("1. Builder Pattern");

		System.out.println();
		System.out.println("---------------------------------------------------------------------------");
	}

	/**
	 * Build Pattern demo.
	 */
	private void builderPatternDemo() {
		// Create builders
		final JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
		final JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		// Create a object model
		final JsonObject data = jsonBuilder.add("name", "Jason Bourne")
				.add("profession", "Super Agent")
				.add("bad-guy", false).add("kills", 1000)
				.add("phoneNumbers", jsonArrayBuilder.add(jsonBuilder
						.add("type", "home")
						.add("number", "123-456-789")))
				.build();

		// Write model to a stream
		StringWriter stringWriter = new StringWriter();
		Map<String, Boolean> config = new HashMap<>();
	    config.put(JsonGenerator.PRETTY_PRINTING, true);
	    JsonWriter jsonWriter = Json.createWriterFactory(config).createWriter(
	               stringWriter);
	    jsonWriter.writeObject(data);
	    jsonWriter.close();
		String str = stringWriter.toString();
		System.out.println(str);
	}
}