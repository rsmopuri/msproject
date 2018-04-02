package com.mastercard.rest.webservice.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Builds connections between cities.
 * */
public class ConnectionsBuilder {
	private static final String DELIMITER = ",";
	/* Buffered reader used to read through loaded data file. */
	private BufferedReader bReader;
	/* Right hand side city in the connection test. */
	private String cityA;
	/* Left hand side city in the connection test. */
	private String cityB;

	public ConnectionsBuilder(final BufferedReader bReader, final String cityA,
			final String cityB) {
		this.bReader = bReader;
		this.cityA = cityA.toLowerCase();
		this.cityB = cityB.toLowerCase();
	}

	/**
	 * Build connections between cities.
	 * 
	 * @return {@link HashMap} containing city name and city node containing
	 *         connections to other cities.
	 * */
	public ConcurrentHashMap<String, City> buildConnections() {
		return processLine();
	}

	/**
	 * Process file data, and build the connections between the cities.
	 * 
	 * @return dataMap {@link HashMap} holding cities connections.
	 * */
	protected final ConcurrentHashMap<String, City> processLine() {
		String line;
		final ConcurrentHashMap<String, City> dataMap = new ConcurrentHashMap<String, City>();
		try {
			while ((line = bReader.readLine()) != null) {
				// Check if direct relation can be found
				String connectedCities[] = parseLine(line);
				saveConnection(dataMap, connectedCities);
			}
		} catch (IOException e) {
			StatusCode.status(e.getMessage(), 1);
		}
		checkNodesExist(dataMap);
		return dataMap;
	}

	/**
	 * Check if both nodes exist in data map, if not terminate.
	 * */
	protected final void checkNodesExist(Map<String, City> data) {
		if (data == null || data.isEmpty() || !data.containsKey(this.cityA)
				|| !data.containsKey(cityB)) {
			StatusCode.no();
		}
	}

	protected void saveConnection(final Map<String, City> dataMap,
			String[] connectedCities) {
		// This is to confirm that we has two cities, Just in case file
		// contained one city.
		if (connectedCities.length == 2) {

			ArrayList<City> cities = new ArrayList<City>(2);
			City city;
			for (String cityName : connectedCities) {
				if ((city = dataMap.get(cityName)) == null) {
					city = new City(cityName);
					dataMap.put(cityName, city);
				}
				cities.add(city);
			}
			if (!cities.get(0).hasConnectionWith(cities.get(1).getName())) {
				cities.get(0).addConnection(cities.get(1).getName(),
						cities.get(1));
			}
			if (!cities.get(1).hasConnectionWith(cities.get(0).getName())) {
				cities.get(1).addConnection(cities.get(0).getName(),
						cities.get(0));
			}
		}
	}

	/**
	 * Process scanned line and checks for direct connection.
	 * 
	 * @param scanned
	 *            line.
	 * @return connected cities.
	 * */
	protected final String[] parseLine(final String line) {
		String connectedCities[] = getConnectedCities(line);
		checkDirectConnection(connectedCities);
		return connectedCities;
	}

	/**
	 * @return array of strings representing connected cities.
	 * */
	protected String[] getConnectedCities(String line) {
		return line.trim().toLowerCase().replace(DELIMITER + " ", DELIMITER)
				.split(DELIMITER);
	}

	/**
	 * Check if there is a direct relation between requested cities and the
	 * current connection.
	 * */
	protected void checkDirectConnection(String[] currentConnection) {
		String[] targetConnections = { this.cityA, this.cityB };
		Arrays.sort(currentConnection);
		Arrays.sort(targetConnections);
		if (currentConnection[0].equals(targetConnections[0])
				&& currentConnection[1].equals(targetConnections[1])) {
			StatusCode.yes();
		}
	}
}
