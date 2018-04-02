package com.mastercard.rest.webservice.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Domain representing city node.
 * */
public class City {
	/* City name. */
	private String name;
	/*
	 * city status representing wither this node has been visited during the
	 * search or not.
	 */
	private boolean visited;
	/* List of cities that are connected to this city. */
	private Map<String, City> connections;

	/**
	 * Constructor
	 * 
	 * @param name
	 *            city name.
	 * */
	public City(final String name) {
		this.name = name;
		this.visited = false;
		connections = new ConcurrentHashMap<String, City>();
	}

	/**
	 * Return city name.
	 * 
	 * @return city name.
	 * */
	public String getName() {
		return this.name;
	}

	/**
	 * Adds a city to the list of connected cities.
	 * 
	 * @param connection
	 *            city that this city is connected to.
	 * */
	public void addConnection(final String cityName, final City connection) {
		this.connections.put(cityName, connection);
	}

	/**
	 * Return list of cities connected to this city.
	 * 
	 * @return {@link List} of {@link City}s.
	 * */
	public Map<String, City> getConnections() {
		return this.connections;
	}

	/**
	 * Check if the city node has connection with the target city.
	 * 
	 * @param cityName
	 *            name of the city to check connection with.
	 * @return <code>true</code> if city is connected with target city,
	 *         <code>false</code> if not.
	 * */
	public boolean hasConnectionWith(final String cityName) {
		if (connections != null && !connections.isEmpty()) {
			return connections.containsKey(cityName);
		}
		return false;
	}

	/**
	 * Return if city was visited or not.
	 * 
	 * @return city status, wither it was visited before or not.
	 */
	public boolean isVisited() {
		return this.visited;
	}

	/**
	 * Change visit status of the city to true.
	 * */
	public void visited() {
		this.visited = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		City other = (City) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

}
