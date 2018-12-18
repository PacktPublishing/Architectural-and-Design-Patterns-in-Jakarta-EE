package com.packtpub.jeepatterns.creational;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import javax.json.stream.JsonParserFactory;

/**
 * Jakarta EE Design Patterns Creational Patterns using JSON Processing
 *
 * @author Werner Keil
 */
public class JsonDemo {

	public static void main(String[] args) {
		if (args != null && args.length > 0) {
			new JsonDemo().start(args[0]);
		} else {
			new JsonDemo().start("");
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
				if (done)
					return;
			}
		}
	}

	private boolean doOption(String option) {
		switch (option) {
		case "q":
		case "Q":
			return true;
		case "2":
			factoryPatternDemo();
			return false;
		case "3":
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
		System.out.println("Section 2 - Creational Patterns");
		System.out.println();
		System.out.println("Choose a pattern to demonstrate or press 'Q' to exit:");
		System.out.println();
		System.out.println("2. Factory Pattern");
		System.out.println("3. Builder Pattern");

		System.out.println();
		System.out.println("---------------------------------------------------------------------------");
	}

	/**
	 * Factory Pattern demo.
	 */
	private void factoryPatternDemo() {
		// Builder factory
		final JsonBuilderFactory builderFactory = Json.createBuilderFactory(null);
		final JsonObjectBuilder jsonBuilder = builderFactory.createObjectBuilder();
		final JsonArrayBuilder jsonArrayBuilder = builderFactory.createArrayBuilder();
		final JsonObject data = jsonBuilder.add("name", "Jason Bourne").add("profession", "Super Agent")
				.add("bad-guy", false).add("kills", 1000)
				.add("phoneNumbers", jsonArrayBuilder.add(jsonBuilder.add("type", "home").add("number", "123-456-789")))
				.build();

		// Writer factory
		final StringWriter stringWriter = new StringWriter();
		final Map<String, Boolean> config = new HashMap<>();
		config.put(JsonGenerator.PRETTY_PRINTING, true);
		final JsonWriterFactory writerfactory = Json.createWriterFactory(config);
		final JsonWriter jsonWriter = writerfactory.createWriter(stringWriter);
		jsonWriter.writeObject(data);
		jsonWriter.close();
		final String str = stringWriter.toString();
		
		// Parser Factory
		final Reader stringReader = new StringReader(str);
		final JsonParserFactory parserFactory = Json.createParserFactory(null);
		final JsonParser jsonParser = parserFactory.createParser(stringReader);
		while (jsonParser.hasNext()) {
			Event e = jsonParser.next();
			System.out.print(e);
			switch (e)
			{
				case KEY_NAME:
				case VALUE_NUMBER:
				case VALUE_STRING:
					System.out.println(": " + jsonParser.getString());
					break;
				default:
					System.out.println();
					break;
				
			}
		}
	}

	/**
	 * Build Pattern demo.
	 */
	private void builderPatternDemo() {
		// Create builders
		final JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
		final JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		// Create a object model
		final JsonObject data = jsonBuilder.add("name", "Jason Bourne").add("profession", "Super Agent")
				.add("bad-guy", false).add("kills", 1000)
				.add("phoneNumbers", jsonArrayBuilder.add(jsonBuilder.add("type", "home").add("number", "123-456-789")))
				.build();

		// Write model to a stream
		StringWriter stringWriter = new StringWriter();
		Map<String, Boolean> config = new HashMap<>();
		config.put(JsonGenerator.PRETTY_PRINTING, true);
		JsonWriter jsonWriter = Json.createWriterFactory(config).createWriter(stringWriter);
		jsonWriter.writeObject(data);
		jsonWriter.close();
		String str = stringWriter.toString();
		System.out.println(str);
	}
}