package com.packtpub.jeepatterns.creational;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.flywaydb.core.Flyway;

public class ObjectPoolDemo {

	public static void main(String[] args) {
		if (args != null && args.length > 0) {
			new ObjectPoolDemo().start(args[0]);
		} else {
			new ObjectPoolDemo().start("");
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
		case "5":
			objectPoolDemo();
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
		System.out.println("5. Object Pool Pattern");

		System.out.println();
		System.out.println("---------------------------------------------------------------------------");
	}

	private static final String DB_DRIVER = "org.h2.Driver";
	private static final String DB_CONNECTION = "jdbc:h2:~/test";
	private static final String DB_USER = "";
	private static final String DB_PASSWORD = "";

	private GenericObjectPool objectPool = null;

	@SuppressWarnings("unused")
	private DataSource setUpPool() throws Exception {
		Class.forName(DB_DRIVER);

		// Creates an Instance of GenericObjectPool That Holds our Connection Pool
		objectPool = new GenericObjectPool();
		objectPool.setMaxActive(5);

		// Creates a ConnectionFactory used by the Pool
		ConnectionFactory cf = new DriverManagerConnectionFactory(DB_CONNECTION, DB_USER, DB_PASSWORD);

		// Creates a PoolableConnectionFactory that wraps the Connection Object
		PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf, objectPool, null, null, false, true);
		return new PoolingDataSource(objectPool);
	}

	private GenericObjectPool getConnectionPool() {
		return objectPool;
	}

	// Print the Connection Pool status
	private void printDbStatus() {
		System.out.println("Max.: " + getConnectionPool().getMaxActive() + "; Active: "
				+ getConnectionPool().getNumActive() + "; Idle: " + getConnectionPool().getNumIdle());
	}

	/**
	 * Object Pool Pattern demo.
	 */
	private void objectPoolDemo() {
		initSchema();

		try {
			DataSource dataSource = setUpPool();
			printDbStatus();

			// Performing DB Operation!
			System.out.println("---------------------------------------------------------------------------");
			System.out.println("Creating a New Connection Object For Db Transaction");
			System.out.println("---------------------------------------------------------------------------");

			try (Connection conn = dataSource.getConnection();
					PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users");) {

				try (ResultSet rs = pstmt.executeQuery();) {
					while (rs.next()) {
						System.out.println("Username: " + rs.getString("name"));
					}
					System.out.println("---------------------------------------------------------------------------");
					System.out.println("Releasing Connection Object To Pool");
					System.out.println("---------------------------------------------------------------------------");
					printDbStatus();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		printDbStatus();
	}

	@SuppressWarnings("deprecation")
	private void initSchema() {
		Flyway flyway = new Flyway();
		flyway.setDataSource(DB_CONNECTION, DB_USER, DB_PASSWORD);
		flyway.migrate();
	}
}