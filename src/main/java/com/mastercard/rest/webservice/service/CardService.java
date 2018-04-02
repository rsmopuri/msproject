package com.mastercard.rest.webservice.service;

import java.io.BufferedReader;
import java.util.concurrent.ConcurrentHashMap;

public class CardService {

	/**
	 * Start Executing the program.
	 * 
	 * @param args
	 *            command line arguments
	 * */
	public static Boolean execute(String originCity, String destinationCity) {

		//Check stupid question.
		checkStupidQuestion(originCity.toLowerCase(), destinationCity.toLowerCase());
		// Loading data file
		FileLoader fileLoader = new FileLoader();
		String cityData = "src/main/resources/static/city.txt";
		 
			BufferedReader fileBufferReader = fileLoader.loadFile(cityData);

		// Building cities connections
		com.mastercard.rest.webservice.service.ConnectionsBuilder connectionBuilder = new com.mastercard.rest.webservice.service.ConnectionsBuilder(
				fileBufferReader, originCity, destinationCity);
		ConcurrentHashMap<String, com.mastercard.rest.webservice.service.City> dataMap = connectionBuilder
				.buildConnections();

		// Search for the desired connection
		ConnectionScanner connectionFinder = new ConnectionScanner(originCity,
				destinationCity, dataMap);
		Boolean areConnectedCities = connectionFinder.checkConnection();
		System.out.println("areConnectedCities execute M"+areConnectedCities);
		return areConnectedCities;
	}

	/**
	 * Basic check for arguments validity.
	 * 
	 * @param args
	 *            command line arguments.
	 * */
	protected static boolean checkArgs(final String args[]) {
		if (args.length == 3) {
			for (String arg : args) {
				if (arg.trim().isEmpty()) {
					return false;
				}
			}
			return true;
		}

		return false;
	}
	
	/**
	 * Checks if inserted cities are the same.
	 * @param cityA starting point.
	 * @param cityB end point.
	 * */
	protected static void checkStupidQuestion(final String cityA,final String cityB){
		if(cityA.trim().toLowerCase().equals(cityB.trim().toLowerCase())){
			StatusCode.yes();
		}
	}
	
	
}
