package com.rauxasoft.liga_futbol.presentation.config;

public class CustomErrorResponse {
	
	private String error;
	
	public CustomErrorResponse(String error) {
		this.error = error;
	}
	
	public String getError() {
		return this.error;
	}

}
