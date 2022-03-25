package com.agpaytech.countriesupdate.responseobjects;

import com.agpaytech.countriesupdate.DummyCountry;

public class NewCountryCDResponse {
    
	private DummyCountry countrydetails;
	private String message;
	
	public NewCountryCDResponse() {
		
	}
	
	public NewCountryCDResponse(DummyCountry countrydetails, String message) {
		this.countrydetails = countrydetails;
		this.message = message;
	}
	public DummyCountry getCountrydetails() {
		return countrydetails;
	}
	public void setCountrydetails(DummyCountry countrydetails) {
		this.countrydetails = countrydetails;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
