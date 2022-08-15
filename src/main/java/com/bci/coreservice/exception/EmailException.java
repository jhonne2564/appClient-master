package com.bci.coreservice.exception;

public class EmailException extends Exception {

	private static final long serialVersionUID = 1L;
	public EmailException() {
		
	}
	public EmailException(String mensaje) {
		super(mensaje);
	}
}
