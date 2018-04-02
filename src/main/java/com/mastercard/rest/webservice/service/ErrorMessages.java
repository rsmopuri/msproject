package com.mastercard.rest.webservice.service;

public enum ErrorMessages {
	FILE_NOT_FOUND(" file not found."), FILE_IS_EMPTY(" file is empty!");
	private String errorMessages;

	private ErrorMessages(String msg) {
		this.errorMessages = msg;
	}

	public final String value() {
		return this.errorMessages;
	}
}
