package com.mastercard.rest.webservice.service;

public class StatusCode {
	public static void status(final String message, final int status) {
		System.out.println(message);
		System.exit(status);
	}

	public static Boolean yes() {
		status("yes", 0);
		
		return true;
	}

	public static Boolean no() {
		status("no", 0);
		
		return false;
	}
}
