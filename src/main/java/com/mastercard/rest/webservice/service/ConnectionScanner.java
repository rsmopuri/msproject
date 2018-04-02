package com.mastercard.rest.webservice.service;

import java.util.concurrent.ConcurrentHashMap;

public class ConnectionScanner {
	private String startPoint;
	private String endPoint;
	private ConcurrentHashMap<String, City> dataMap;

	public ConnectionScanner(final String startPoint, final String endPoint,
			final ConcurrentHashMap<String, City> dataMap) {
		this.startPoint = startPoint.toLowerCase();
		this.endPoint = endPoint.toLowerCase();
		this.dataMap = dataMap;
	}

	/**
	 * Checks if there is a connection between provided starting point and end
	 * point.
	 * */
	public Boolean checkConnection() {
		if (isConnected()) {
			StatusCode.yes();
			System.out.println("checkConnection M "+StatusCode.yes());
			return StatusCode.yes();
		} else {
			StatusCode.no();
			System.out.println("checkConnection M "+StatusCode.no());
			return StatusCode.no();
		}
	}

	/**
	 * @return false if there is no connection between provided cities.
	 * */
	protected boolean isConnected() {
		City startCity = dataMap.get(startPoint);
		search(startCity);
		return false;
	}

	/**
	 * Used to search for connection.
	 * 
	 * @param currentNode
	 *            to check.
	 * */
	protected void search(City currentNode) {
		if (currentNode.getName().equals(endPoint)) {
			StatusCode.yes();
		}
		// Clean CityA nodes
		cleanNodes(currentNode);
		if (!currentNode.isVisited()) {
			currentNode.visited();
		} else {
			return;
		}

		for (City node : currentNode.getConnections().values()) {
			search(node);
		}
	}

	/**
	 * Used to remove connections with visited node to prevent circular path
	 * 
	 * */
	protected void cleanNodes(City A) {
		for (City city : A.getConnections().values()) {
			city.getConnections().remove(A.getName());
		}
	}

}
