package com.rauxasoft.liga_futbol.presentation.config;

import org.springframework.http.HttpStatus;

public class PresentationException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	private final HttpStatus httpStatus;
	
	public PresentationException(String mensaje, HttpStatus httpStatus) {
		super(mensaje);
		this.httpStatus = httpStatus;
	}
	
	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}

}
